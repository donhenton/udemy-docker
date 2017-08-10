FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/spring-boot-redis-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8100
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]