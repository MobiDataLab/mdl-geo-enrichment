package eu.akka.mobidata.mashup.filter;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Component
public class RateLimitFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitFilter.class);
    // @TODO externalize rate limit
    private final int MAX_REQUESTS_PER_SECOND = 5;

    private LoadingCache<String, Integer> requestCountsPerIpAddress;

    public RateLimitFilter() {
        super();
        requestCountsPerIpAddress = Caffeine.newBuilder().
                expireAfterWrite(1, TimeUnit.MINUTES).build(key -> 0);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (!httpServletRequest.getRequestURI().startsWith("/swagger")) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            String clientIpAddress = getClientIP((HttpServletRequest) servletRequest);
            if (isMaximumRequestsPerExpiryTimeExceeded(clientIpAddress)) {
                httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                httpServletResponse.getWriter().write(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
                LOGGER.debug("Too many requests for client ip address: {}!", clientIpAddress);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isMaximumRequestsPerExpiryTimeExceeded(String clientIpAddress) {
        Integer requests = requestCountsPerIpAddress.get(clientIpAddress);
        if (requests != null) {
            if (requests > MAX_REQUESTS_PER_SECOND) {
                // restart expiry time
                requestCountsPerIpAddress.asMap().remove(clientIpAddress);
                requestCountsPerIpAddress.put(clientIpAddress, requests);
                return true;
            } else {
                requestCountsPerIpAddress.put(clientIpAddress, ++requests);
            }
        } else {
            //first request
            requestCountsPerIpAddress.put(clientIpAddress, 1);
        }
        return false;
    }

    public String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}