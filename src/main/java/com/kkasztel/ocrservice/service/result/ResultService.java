package com.kkasztel.ocrservice.service.result;

import com.kkasztel.ocrservice.service.model.Result;

import io.vavr.control.Option;

public interface ResultService {

    Result save(Result result);

    Option<Result> findById(String id);
}
