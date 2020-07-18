package com.kkasztel.ocrservice.service.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class File {

    String name;
    byte[] content;
    String contentType;
}
