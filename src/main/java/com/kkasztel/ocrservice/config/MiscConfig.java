package com.kkasztel.ocrservice.config;

import org.apache.tika.config.TikaConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiscConfig {

    @Bean
    public TikaConfig tikaConfig() {
        return TikaConfig.getDefaultConfig();
    }
}
