package eu.akka.mobidata.mashup.filter;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import eu.akka.mobidata.mashup.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Handles Rate Limit Filter
 *
 * @author Mohamed.KARAMI
 */
@Component
public class RateLimitFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitFilter.class);
    private static final AntPathMatcher antMatcher = new AntPathMatcher();
    private int requestsRateLimit;

    private LoadingCache<String, Integer> requestCountsPerIpAddress;

    public RateLimitFilter(@Value("${server.requests-rate-limit}") int requestsRateLimit) {
        super();

        this.requestsRateLimit = requestsRateLimit;
        requestCountsPerIpAddress = Caffeine.newBuilder().
                expireAfterWrite(1, TimeUnit.MINUTES).build(key -> 0);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        boolean allowed = Arrays.stream(Constants.AUTH_LIST).anyMatch(patern -> antMatcher.match(patern, httpServletRequest.getRequestURI()));

        if (!allowed) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            String clientIpAddress = getClientIP((HttpServletRequest) servletRequest);
            if (isMaximumRequestsPerExpiryTimeExceeded(clientIpAddress)) {
                LOGGER.error("Too many requests for client ip address: {}!", clientIpAddress);
                httpServletResponse.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isMaximumRequestsPerExpiryTimeExceeded(String clientIpAddress) {
        Integer requests = requestCountsPerIpAddress.get(clientIpAddress);
        if (requests != null) {
            if (requests > requestsRateLimit) {
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