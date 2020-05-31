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
@Document(collection = "job")
@AccessType(AccessType.Type.PROPERTY)
public class JobEntity extends AbstractAuditingEntity {

    @NotNull String input;

    public static JobEntity of(String id, Instant creationTime, Instant expirationTime, String input) {
        return new JobEntity(id, creationTime, expirationTime, input);
    }

    @Builder
    @JsonCreator
    @PersistenceConstructor
    public JobEntity(String id, Instant creationTime, Instant expirationTime, String input) {
        super(id, creationTime, expirationTime);
        this.input = input;
    }
}
