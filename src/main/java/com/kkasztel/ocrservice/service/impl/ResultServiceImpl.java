package com.kkasztel.ocrservice.service.impl;

import com.kkasztel.ocrservice.domain.repository.ResultRepository;
import com.kkasztel.ocrservice.service.ResultService;
import com.kkasztel.ocrservice.service.mapper.ResultMapper;
import com.kkasztel.ocrservice.service.model.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vavr.control.Option;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository repository;
    private final ResultMapper mapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ResultServiceImpl(ResultRepository repository, ResultMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Result save(Result result) {
        return mapper.resultEntityToResult(repository.save(mapper.resultToResultEntity(result)));
    }

    @Override
    public Option<Result> findById(String id) {
        return Option.ofOptional(repository.findById(id)).map(mapper::resultEntityToResult);
    }
}
