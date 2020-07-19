package com.kkasztel.ocrservice.config;

import com.kkasztel.ocrservice.config.property.Messaging;
import com.kkasztel.ocrservice.config.property.Minio;
import com.kkasztel.ocrservice.config.property.StorageTime;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Value;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class AppProps {

    Minio minio;
    StorageTime storageTime;
    Messaging messaging;
}
