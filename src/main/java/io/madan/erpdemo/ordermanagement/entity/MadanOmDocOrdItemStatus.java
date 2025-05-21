package io.madan.erpdemo.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "madan_om_docorditemstatus")
@Data
public class MadanOmDocOrdItemStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SerialNo")
    private Integer serialNo;

    @Column(name = "DocOrderNo")
    private String docOrderNo;

    @Column(name = "DocOrderType")
    private String docOrderType;

    @Column(name = "ItemNo")
    private Integer itemNo;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "StatusDate")
    private Instant statusDate;

    @Column(name = "Remarks")
    private String remarks;

}
