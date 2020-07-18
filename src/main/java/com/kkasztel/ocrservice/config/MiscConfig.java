package com.kkasztel.ocrservice.config;

import org.apache.tika.config.TikaConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class MiscConfig {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public TikaConfig tikaConfig() {
        return TikaConfig.getDefaultConfig();
    }
}
