package io.madan.erpdemo.ordermanagement.controller;

import io.madan.erpdemo.common.dto.ApiResponse;
import io.madan.erpdemo.ordermanagement.dto.DocHeadDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderItemDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderRequestDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderResponseDto;
import io.madan.erpdemo.ordermanagement.dto.OrderFilterDto;
import io.madan.erpdemo.ordermanagement.dto.StatusUpdateReportDTO;
import io.madan.erpdemo.ordermanagement.service.DocOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class DocOrderController {

    private DocOrderService docOrderService;

    @Autowired
    public DocOrderController(DocOrderService docOrderService) {
        this.docOrderService = docOrderService;
    }

    //Create Doc Order with Items
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DocOrderResponseDto> createOrderWithItems(@RequestBody DocOrderRequestDto dto){

        DocOrderResponseDto responseDto = this.docOrderService.saveOrderWithItems(dto);

        return ApiResponse.<DocOrderResponseDto>builder()
                .success(true)
                .data(responseDto)
                .build();
    }

    //Get All Doc orders
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<DocHeadDto>> getAllOrders(){

        List<DocHeadDto> ordersList = this.docOrderService.findAllOrders();

        return ApiResponse.<List<DocHeadDto>>builder()
                .success(true)
                .data(ordersList)
                .build();
    }

    //Get All Items for a Doc order
    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<DocOrderItemDto>> getAllItemsPerOrders(OrderFilterDto dto){

        List<DocOrderItemDto> orderItemsList = this.docOrderService.findAllItemsForOrder(dto);

        return ApiResponse.<List<DocOrderItemDto>>builder()
                .success(true)
                .data(orderItemsList)
                .build();
    }

    //Update multiple items in an order
    @PutMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<DocOrderResponseDto> updateItemsOfAnOrder(@RequestBody DocOrderRequestDto dto){

        DocOrderResponseDto responseDto = this.docOrderService.updateItemsOfAnOrder(dto);

        return ApiResponse.<DocOrderResponseDto>builder()
                .success(true)
                .data(responseDto)
                .build();
    }

    //GET status update report
    @GetMapping("/items/status/report")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<StatusUpdateReportDTO>> updateItemsOfAnOrder(){

        List<StatusUpdateReportDTO> statusUpdateReport = this.docOrderService.generateStatusUpdateReport();

        return ApiResponse.<List<StatusUpdateReportDTO>>builder()
                .success(true)
                .data(statusUpdateReport)
                .build();
    }
}
