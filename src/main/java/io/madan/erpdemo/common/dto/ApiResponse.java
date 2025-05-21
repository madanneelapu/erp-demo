package io.madan.erpdemo.common.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiResponse<T> {
    private T data;
    @Builder.Default
    private Instant timestamp = Instant.now();
    private boolean success;

}
