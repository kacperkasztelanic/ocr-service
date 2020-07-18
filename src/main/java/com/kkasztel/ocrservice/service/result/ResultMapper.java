package com.kkasztel.ocrservice.service.result;

import com.kkasztel.ocrservice.domain.entity.ResultEntity;
import com.kkasztel.ocrservice.service.model.Result;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import io.vavr.control.Option;

@Mapper(componentModel = "spring")
public interface ResultMapper {

    @Mapping(source = "results", target = "results", qualifiedByName = "unwrap")
    @Mapping(source = "exception", target = "exception", qualifiedByName = "unwrap")
    ResultEntity resultToResultEntity(Result result);

    Result resultEntityToResult(ResultEntity resultEntity);

    @Named("unwrap")
    default <T> T unwrap(Option<T> option) {
        return option.getOrNull();
    }
}
