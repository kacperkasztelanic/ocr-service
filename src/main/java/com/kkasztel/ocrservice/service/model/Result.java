package com.kkasztel.ocrservice.service.model;

import java.time.Instant;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import static io.vavr.API.Option;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = { "id" })
@ToString(includeFieldNames = false)
public class Result {

    String id;
    Instant creationTime;
    Instant expirationTime;
    String jobId;
    String results;
    String exception;

    public Option<String> getResults() {
        return Option(results);
    }

    public Option<String> getException() {
        return Option(exception);
    }

    public boolean isSuccessful() {
        return exception == null;
    }
}
