package io.madan.erpdemo.ordermanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class DocHeadDto {

    private String docOrderNo;
    private String docOrderType;
    private Instant receivedDate;
    private String receivedDateUserFormat;
    private String reqBy;
    private BigDecimal totalAmount;
    private Integer status; // use enum if needed


}
