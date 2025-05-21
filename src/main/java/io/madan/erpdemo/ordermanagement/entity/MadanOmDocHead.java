package io.madan.erpdemo.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "madan_om_dochead")
@Data
public class MadanOmDocHead {

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

    @Column(name = "ReqBy")
    private String reqBy;

    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;

    @Column(name = "Status")
    private Integer status;
}
