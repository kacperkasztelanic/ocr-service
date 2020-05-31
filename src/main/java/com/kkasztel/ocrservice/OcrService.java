package com.kkasztel.ocrservice;

import com.kkasztel.ocrservice.config.AppProps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(AppProps.class)
public class OcrService {

    private static final Logger log = LoggerFactory.getLogger(OcrService.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OcrService.class);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String appName = env.getProperty("spring.application.name");
        String protocol = "http";
        String serverPort = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------" +//
                        "\n\tApplication '{}' is running!" +//
                        "\n\tURL: \t\t{}://localhost:{}" +//
                        "\n----------------------------------------------------------",//
                appName,//
                protocol,//
                serverPort//
        );
    }
}
