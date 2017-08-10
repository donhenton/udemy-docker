docker run  --rm -d --name=redis-server redis:3.2.0  && \
docker run  --rm -d -e REDIS_HOST=redis-server --name=redis-client --link=redis-server  -p 8100:8100 donhenton/spring-boot-template:redis-client

