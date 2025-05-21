package io.madan.erpdemo.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Table(name = "madan_om_docorderitems")
@Data
public class MadanOmDocOrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SerialNo")
    private Integer serialNo;

    @Column(name = "DocOrderNo")
    private String docOrderNo;

    @Column(name = "DocOrderType")
    private String docOrderType;

    @Column(name = "ReceivedDate")
    private Instant receivedDate;

    @Column(name = "ItemNo")
    private Integer itemNo;

    @Column(name = "ItemCode")
    private String itemCode;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Quantity")
    private BigDecimal quantity;

    @Column(name = "TotAmount")
    private BigDecimal totAmount;

    @Column(name = "Priority")
    private String priority;

    @Column(name = "Status")
    private Integer status;
}
