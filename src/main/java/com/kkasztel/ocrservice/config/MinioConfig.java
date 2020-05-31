package com.kkasztel.ocrservice.config;

import com.kkasztel.ocrservice.config.property.Minio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

@Configuration
public class MinioConfig {

    private final AppProps appProps;

    @Autowired
    public MinioConfig(AppProps appProps) {
        this.appProps = appProps;
    }

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        Minio minio = appProps.getMinio();
        return new MinioClient(//
                minio.getHost(),//
                minio.getPort(),//
                minio.getAccessKey(),//
                minio.getSecretKey(),//
                minio.getSsl()//
        );
    }
}
