package com.kkasztel.ocrservice.service.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = { "id" })
@ToString(includeFieldNames = false)
public class Job {

    String id;
    Instant creationTime;
    Instant expirationTime;
    String input;
}
