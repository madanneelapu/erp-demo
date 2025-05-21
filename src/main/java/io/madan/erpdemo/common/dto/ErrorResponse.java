package io.madan.erpdemo.common.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorResponse {

    private String errorCode;
    private String message;
    private Instant timestamp;

    public ErrorResponse() {
        this.timestamp = Instant.now();
    }

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = Instant.now();
    }
}
