package io.madan.erpdemo.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class StatusUpdateReportDTO {
    private String docOrderNo;
    private Integer headStatus;
    private Integer itemNo;
    private String itemCode;
    private BigDecimal itemTotalAmount;
    private Long itemUpdateCount;

}
