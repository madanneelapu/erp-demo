package io.madan.erpdemo.ordermanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class DocOrderRequestDto {
    private DocHeadDto docHead;
    private List<DocOrderItemDto> items;
}
