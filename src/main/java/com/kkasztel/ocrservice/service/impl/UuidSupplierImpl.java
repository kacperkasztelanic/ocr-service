package com.kkasztel.ocrservice.service.impl;

import com.kkasztel.ocrservice.service.UuidSupplier;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidSupplierImpl implements UuidSupplier {

    @Override
    public String get() {
        return UUID.randomUUID().toString();
    }
}
