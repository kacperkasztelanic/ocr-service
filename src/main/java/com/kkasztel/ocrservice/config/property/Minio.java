package com.kkasztel.ocrservice.config.property;

import lombok.Value;

@Value
public class Minio {

    String host;
    Integer port;
    Boolean ssl;
    String accessKey;
    String secretKey;
    String bucket;
}
