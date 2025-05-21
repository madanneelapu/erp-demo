package io.madan.erpdemo.ordermanagement.service.impl;

import io.madan.erpdemo.common.util.CustomIdGenerator;
import io.madan.erpdemo.common.util.DateTimeUtil;
import io.madan.erpdemo.ordermanagement.dto.DocHeadDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderItemDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderRequestDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderResponseDto;
import io.madan.erpdemo.ordermanagement.dto.OrderFilterDto;
import io.madan.erpdemo.ordermanagement.dto.StatusUpdateReportDTO;
import io.madan.erpdemo.ordermanagement.repository.DocOrderJdbcRepository;
import io.madan.erpdemo.ordermanagement.repository.DocOrderRepository;
import io.madan.erpdemo.ordermanagement.service.DocOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocOrderServiceImpl implements DocOrderService {


    DocOrderRepository docOrderRepository;
    DocOrderJdbcRepository docOrderJdbcRepository;

    @Autowired
    public DocOrderServiceImpl(DocOrderRepository docOrderRepository,
                               DocOrderJdbcRepository docOrderJdbcRepository) {
        this.docOrderRepository = docOrderRepository;
        this.docOrderJdbcRepository = docOrderJdbcRepository;
    }

    @Override
    @Transactional
    public DocOrderResponseDto saveOrderWithItems(DocOrderRequestDto dto) {

        // Generate ID pattern - Not dependent on DB
        String docOrderType = dto.getDocHead().getDocOrderType();
        String newDocOrderId = CustomIdGenerator.generateId(docOrderType);
        dto.getDocHead().setDocOrderNo(newDocOrderId);

        return this.docOrderRepository.saveOrderWithItems(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocHeadDto> findAllOrders() {
        return this.docOrderRepository.findAllOrders();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocOrderItemDto> findAllItemsForOrder(OrderFilterDto dto) {
        return this.docOrderRepository.findAllItemsForOrder(dto);
    }

    @Override
    @Transactional
    public DocOrderResponseDto updateItemsOfAnOrder(DocOrderRequestDto dto) {
        // some native SQL op

        //this.docOrderJdbcRepository.callMethod();

        return this.docOrderRepository.updateItemsOfAnOrder(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatusUpdateReportDTO> generateStatusUpdateReport() {
        return this.docOrderRepository.generateStatusUpdateReport();
    }


}
