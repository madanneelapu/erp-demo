package io.madan.erpdemo.ordermanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderFilterDto {
    private String docOrderNo;
    private String docOrderType;
}
