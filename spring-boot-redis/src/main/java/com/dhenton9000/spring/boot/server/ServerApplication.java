package com.dhenton9000.spring.boot.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import redis.clients.jedis.Jedis;

public class ServerApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(MainBoot.class);
        app.run(args);
    }
}

@SpringBootApplication

class MainBoot {

    private String serverPort;
    private String redisHost;
    private int redisPort;

    private static final Logger LOG = LoggerFactory.getLogger(ServerApplication.class);

    public MainBoot(@Value("${server.port}") String s,
            @Value("${spring.freemarker.template-loader-path}") String fPath,
            @Value("${redis.port}") int redisPort,
            @Value("${redis.host}") String redisHost) {

        serverPort = s;
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        LOG.info("starting server on port " + serverPort);
        LOG.info("freemark location " + fPath);
        LOG.info("host " + redisHost);
    }

    @Bean
    public Jedis createJedis() {
        return new Jedis(redisHost, redisPort);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage custom404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            container.addErrorPages(custom404Page);

        });
    }

}
