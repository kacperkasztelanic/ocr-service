package com.kkasztel.ocrservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

import javax.validation.constraints.NotNull;

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

    @NotNull
    String jobId;
    String results;
    String exception;

    public static ResultEntity of(String id, Instant creationTime, Instant expirationTime, String jobId, String results,
            String exception) {
        return new ResultEntity(id, creationTime, expirationTime, jobId, results, exception);
    }

    @Builder
    @JsonCreator
    @PersistenceConstructor
    public ResultEntity(String id, Instant creationTime, Instant expirationTime, String jobId, String results,
            String exception) {
        super(id, creationTime, expirationTime);
        this.jobId = jobId;
        this.results = results;
        this.exception = exception;
    }
}
