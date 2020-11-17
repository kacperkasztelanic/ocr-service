package com.kkasztel.ocrservice.config;

import com.kkasztel.ocrservice.config.property.Minio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {

    private final AppProps appProps;

    @Autowired
    public MinioConfig(AppProps appProps) {
        this.appProps = appProps;
    }

    @Bean
    public MinioClient minioClient() {
        Minio minio = appProps.getMinio();
        return MinioClient.builder()//
                .endpoint(minio.getHost(), minio.getPort(), minio.getSsl())//
                .credentials(minio.getAccessKey(), minio.getSecretKey())//
                .build();
    }
}
