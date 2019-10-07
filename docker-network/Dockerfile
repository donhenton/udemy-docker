FROM phusion/baseimage:master

RUN apt-get update && \
    apt-get install -yq --no-install-recommends wget iputils-ping pwgen ca-certificates && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*