package com.kkasztel.ocrservice.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
@ToString(includeFieldNames = false)
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class AbstractAuditingEntity {

    @Id
    String id;
    @NotNull
    @Indexed
    Instant creationTime;
}
