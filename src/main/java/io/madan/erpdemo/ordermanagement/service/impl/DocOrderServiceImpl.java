package io.madan.erpdemo.ordermanagement.service.impl;

import io.madan.erpdemo.common.dto.NotificationDTO;
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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocOrderServiceImpl implements DocOrderService {


    DocOrderRepository docOrderRepository;
    DocOrderJdbcRepository docOrderJdbcRepository;

    private final KafkaTemplate<String, NotificationDTO> kafkaTemplate;

    @Autowired
    public DocOrderServiceImpl(DocOrderRepository docOrderRepository,
                               DocOrderJdbcRepository docOrderJdbcRepository,
                               KafkaTemplate<String, NotificationDTO> kafkaTemplate) {
        this.docOrderRepository = docOrderRepository;
        this.docOrderJdbcRepository = docOrderJdbcRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public DocOrderResponseDto saveOrderWithItems(DocOrderRequestDto dto) {

        // Generate ID pattern - Not dependent on DB
        String docOrderType = dto.getDocHead().getDocOrderType();
        String newDocOrderId = CustomIdGenerator.generateId(docOrderType);
        dto.getDocHead().setDocOrderNo(newDocOrderId);

        DocOrderResponseDto docOrderResponseDto = this.docOrderRepository.saveOrderWithItems(dto);


        NotificationDTO notifDto = NotificationDTO.builder()
                .orderId(docOrderResponseDto.getDocOrderNo())
                .customerEmail("testemail@gmail.com")
                .customerPhone("7894561230")
                .message("Order placed successfully.")
                .build();

        kafkaTemplate.send("notification-topic", docOrderResponseDto.getDocOrderNo(), notifDto)
                .thenAccept(result -> System.out.println("Kafka send success: "+ result.getRecordMetadata()))
                .exceptionally(ex -> {
                    System.out.println("Kafka send failed: " + ex.getMessage());
                    return null;
                });;

        System.out.println("Published order No. to Kafka: " + notifDto);

        return docOrderResponseDto;
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
