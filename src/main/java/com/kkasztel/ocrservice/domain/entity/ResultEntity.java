package com.kkasztel.ocrservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode(of = {}, callSuper = true)
@ToString(includeFieldNames = false)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Document(collection = "result")
@AccessType(AccessType.Type.PROPERTY)
public class ResultEntity extends AbstractAuditingEntity {

    String results;
    String exception;

    public static ResultEntity of(String id, Instant creationTime, String results, String exception) {
        return new ResultEntity(id, creationTime, results, exception);
    }

    @Builder
    @JsonCreator
    @PersistenceConstructor
    public ResultEntity(String id, Instant creationTime, String results, String exception) {
        super(id, creationTime);
        this.results = results;
        this.exception = exception;
    }
}
