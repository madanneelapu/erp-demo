package io.madan.erpdemo.ordermanagement.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class DocOrderItemStatusDto {

    private String docOrderNo;
    private String docOrderType;
    private Integer itemNo;
    private Integer status;
    private Instant statusDate;
    private String remarks;
}
