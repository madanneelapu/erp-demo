package io.madan.erpdemo.ordermanagement.repository.impl;

import io.madan.erpdemo.common.util.DateTimeUtil;
import io.madan.erpdemo.ordermanagement.dto.DocHeadDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderItemDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderRequestDto;
import io.madan.erpdemo.ordermanagement.dto.DocOrderResponseDto;
import io.madan.erpdemo.ordermanagement.dto.OrderFilterDto;
import io.madan.erpdemo.ordermanagement.dto.StatusUpdateReportDTO;
import io.madan.erpdemo.ordermanagement.entity.MadanOmDocHead;
import io.madan.erpdemo.ordermanagement.entity.MadanOmDocOrdItemStatus;
import io.madan.erpdemo.ordermanagement.entity.MadanOmDocOrderItems;
import io.madan.erpdemo.ordermanagement.repository.DocOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DocOrderRepositoryImpl implements DocOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DocOrderResponseDto saveOrderWithItems(DocOrderRequestDto dto) {

        DocHeadDto docHeadDto = dto.getDocHead();
        List<DocOrderItemDto> itemsDtoList = dto.getItems();

        Instant recordCrDateTime = Instant.now();
        String docOrderNo = docHeadDto.getDocOrderNo();


        //Head fields
        MadanOmDocHead docHead = new MadanOmDocHead();
        docHead.setDocOrderNo(docOrderNo);
        docHead.setDocOrderType(docHeadDto.getDocOrderType());
        docHead.setReceivedDate(recordCrDateTime);
        docHead.setReqBy(docHeadDto.getReqBy());
        docHead.setTotalAmount(docHeadDto.getTotalAmount());
        docHead.setStatus(2);

        entityManager.persist(docHead); // Inserts a row

        for(DocOrderItemDto docItemDto : itemsDtoList){

            // Item table
            MadanOmDocOrderItems docOrderItem = new MadanOmDocOrderItems();

            //Item fields from headDto
            docOrderItem.setDocOrderNo(docOrderNo);
            docOrderItem.setDocOrderType(docHeadDto.getDocOrderType());
            docOrderItem.setReceivedDate(recordCrDateTime);

            //Item fields from itemDto
            docOrderItem.setItemNo(docItemDto.getItemNo());
            docOrderItem.setItemCode(docItemDto.getItemCode());
            docOrderItem.setPrice(docItemDto.getPrice());
            docOrderItem.setQuantity(docItemDto.getQuantity());
            BigDecimal itemTotalAmt = docItemDto.getQuantity().multiply(docItemDto.getPrice());
            docOrderItem.setTotAmount(itemTotalAmt);
            docOrderItem.setPriority(docItemDto.getPriority());
            docOrderItem.setStatus(2);

            entityManager.persist(docOrderItem);

            // Item status table
            MadanOmDocOrdItemStatus docOrderItemStatus = new MadanOmDocOrdItemStatus();

            //Item status fields from headDto
            docOrderItemStatus.setDocOrderNo(docOrderNo);
            docOrderItemStatus.setDocOrderType(docHeadDto.getDocOrderType());

            //Item status fields from itemDto
            docOrderItemStatus.setItemNo(docItemDto.getItemNo());
            docOrderItemStatus.setStatus(2);
            docOrderItemStatus.setStatusDate(recordCrDateTime);
            docOrderItemStatus.setRemarks("Sample remarks");

            entityManager.persist(docOrderItemStatus);
        }

        return DocOrderResponseDto.builder().docOrderNo(docOrderNo).build();
    }

    @Override
    public List<DocHeadDto> findAllOrders() {
        String findAllJpql = "SELECT o FROM MadanOmDocHead o";
        List<MadanOmDocHead> orderHeadList = entityManager.createQuery(findAllJpql, MadanOmDocHead.class)
                                                    .getResultList();

        List<DocHeadDto> ordersDtoList = new ArrayList<>();
        for (MadanOmDocHead order : orderHeadList){
            ordersDtoList.add(
                DocHeadDto.builder()
                        .docOrderNo(order.getDocOrderNo())
                        .docOrderType(order.getDocOrderType())
                        .receivedDate(order.getReceivedDate())
                        .receivedDateUserFormat(DateTimeUtil.formatInstantToZone(order.getReceivedDate(), "IST"))
                        .reqBy(order.getReqBy())
                        .totalAmount(order.getTotalAmount())
                        .status(order.getStatus())
                        .build()
            );
        }

        return ordersDtoList;

    }

    @Override
    public List<DocOrderItemDto> findAllItemsForOrder(OrderFilterDto dto) {

        String findAllItemsJpql = "SELECT oi FROM MadanOmDocOrderItems oi WHERE oi.docOrderNo = :docOrderNo and oi.docOrderType = :docOrderType";

        List<MadanOmDocOrderItems> orderItemsList = entityManager.createQuery(findAllItemsJpql, MadanOmDocOrderItems.class)
                .setParameter("docOrderNo" , dto.getDocOrderNo())
                .setParameter("docOrderType", dto.getDocOrderType())
                .getResultList();

        List<DocOrderItemDto> orderItemsDtoList = new ArrayList<>();
        for (MadanOmDocOrderItems orderItem : orderItemsList){
            orderItemsDtoList.add(
                    DocOrderItemDto.builder()
                            .docOrderNo(orderItem.getDocOrderNo())
                            .docOrderType(orderItem.getDocOrderNo())
                            .receivedDate(orderItem.getReceivedDate())
                            .itemNo(orderItem.getItemNo())
                            .itemCode(orderItem.getItemCode())
                            .price(orderItem.getPrice())
                            .quantity(orderItem.getQuantity())
                            .totAmount(orderItem.getTotAmount())
                            .priority(orderItem.getPriority())
                            .status(orderItem.getStatus())
                            .build()
            );
        }

        return orderItemsDtoList;

    }

    @Override
    public DocOrderResponseDto updateItemsOfAnOrder(DocOrderRequestDto dto) {

        DocHeadDto docHeadDto = dto.getDocHead();
        List<DocOrderItemDto> itemsDtoList = dto.getItems();

        Instant recordChDateTime = Instant.now();
        String docOrderNo = docHeadDto.getDocOrderNo();

        for(DocOrderItemDto docItemDto : itemsDtoList) {

            //UPDATE: Item table
            String updateItemJpql = """
                    UPDATE MadanOmDocOrderItems oi 
                    SET oi.quantity = :quantity, oi.status = :status
                    WHERE oi.docOrderNo = :docOrderNo
                    AND oi.docOrderType = :docOrderType
                    AND oi.itemNo = :itemNo
                    AND oi.itemCode = :itemCode
                    """;

            entityManager.createQuery(updateItemJpql)
                    .setParameter("quantity", docItemDto.getQuantity())
                    .setParameter("status", docItemDto.getStatus())
                    .setParameter("docOrderNo", docOrderNo)
                    .setParameter("docOrderType", docHeadDto.getDocOrderType())
                    .setParameter("itemNo", docItemDto.getItemNo())
                    .setParameter("itemCode", docItemDto.getItemCode())
                    .executeUpdate();


            // INSERT: Item status table
            MadanOmDocOrdItemStatus docOrderItemStatus = new MadanOmDocOrdItemStatus();

            //Item status fields from headDto
            docOrderItemStatus.setDocOrderNo(docOrderNo);
            docOrderItemStatus.setDocOrderType(docHeadDto.getDocOrderType());

            //Item status fields from itemDto
            docOrderItemStatus.setItemNo(docItemDto.getItemNo());
            docOrderItemStatus.setStatus(docItemDto.getStatus());
            docOrderItemStatus.setStatusDate(recordChDateTime);
            docOrderItemStatus.setRemarks("Updated Sample remarks");

            entityManager.persist(docOrderItemStatus);

        }

        return DocOrderResponseDto.builder()
                .docOrderNo(docOrderNo)
                .itemCodes(itemsDtoList.stream().map(DocOrderItemDto::getItemCode).toList())
                .build();
    }

    @Override
    public List<StatusUpdateReportDTO> generateStatusUpdateReport() {

        String nativeQuery = """
                SELECT 
                    h.DocOrderNo,
                    h.Status AS HeadStatus,
                    i.ItemNo,
                    i.ItemCode,
                    i.TotAmount,
                    COUNT(s.SerialNo) AS ItemUpdateCount
                FROM 
                    madan_om_dochead h
                JOIN 
                    madan_om_docorderitems i 
                    ON h.DocOrderNo = i.DocOrderNo 
                    AND h.DocOrderType = i.DocOrderType
                LEFT JOIN 
                    madan_om_docorditemstatus s 
                    ON i.DocOrderNo = s.DocOrderNo 
                    AND i.DocOrderType = s.DocOrderType 
                    AND i.ItemNo = s.ItemNo
                GROUP BY 
                    h.DocOrderNo,
                    h.Status,
                    i.ItemNo,
                    i.ItemCode,
                    i.TotAmount;
                
                """;

        List<StatusUpdateReportDTO> StatusUpdateReportList = entityManager.createNativeQuery(nativeQuery)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setTupleTransformer((tuple, aliases) ->
                        new StatusUpdateReportDTO(
                                (String) tuple[0],
                                (Integer) tuple[1],
                                (Integer) tuple[2],
                                (String) tuple[3],
                                (BigDecimal) tuple[4],
                                (Long) tuple[5]
                        )
                )
                .getResultList();

        return StatusUpdateReportList;

    }


}
