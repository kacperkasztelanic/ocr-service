package com.kkasztel.ocrservice.service.uuid;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class UuidSupplierImpl implements UuidSupplier {

    @Override
    public String get() {
        return UUID.randomUUID().toString();
    }
}
