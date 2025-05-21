package io.madan.erpdemo.ordermanagement.repository;

import io.madan.erpdemo.ordermanagement.dto.DocHeadDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderItemDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderRequestDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderResponseDto;
import io.madan.erpdemo.ordermanagement.dto.OrderFilterDto;
import io.madan.erpdemo.ordermanagement.dto.StatusUpdateReportDTO;

import java.util.List;

public interface DocOrderRepository {

    DocOrderResponseDto saveOrderWithItems(DocOrderRequestDto dto);

    List<DocHeadDto> findAllOrders();

    List<DocOrderItemDto> findAllItemsForOrder(OrderFilterDto dto);

    DocOrderResponseDto updateItemsOfAnOrder(DocOrderRequestDto dto);

    List<StatusUpdateReportDTO> generateStatusUpdateReport();
}
