package io.madan.erpdemo.ordermanagement.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class DocOrderItemDto {

    private String docOrderNo;
    private String docOrderType;
    private Instant receivedDate;
    private Integer itemNo;
    private String itemCode;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal totAmount;
    private String priority;
    private Integer status;

}
