package io.madan.erpdemo.ordermanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocOrderResponseDto {
    private String docOrderNo;
    private List<String> itemCodes;
}
