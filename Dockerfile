# docker image to be used for build & test MobiDataMashup
FROM maven:3.8.5-openjdk-11-slim

RUN apt-get install update -y curl \
  && curl -sL https://deb.nodesource.com/setup_16.x | bash - \
  && apt-get install -y nodejs \
  && curl -L https://www.npmjs.com/install.sh | sh \

RUN npm install -g osmtogeojson