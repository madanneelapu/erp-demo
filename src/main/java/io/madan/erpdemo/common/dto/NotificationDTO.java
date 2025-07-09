package io.madan.erpdemo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO implements Serializable {
    private String orderId;
    private String customerEmail;
    private String customerPhone;
    private String message;

    // Getters and setters
}