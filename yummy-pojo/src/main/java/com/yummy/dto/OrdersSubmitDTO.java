package com.yummy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersSubmitDTO implements Serializable {

    private Long addressBookId;

    private int payMethod;

    private String remark;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;
    // 1: deliver now; 0: choose a time
    private Integer deliveryStatus;

    private Integer tablewareNumber;

    private Integer tablewareStatus;

    private Integer packingFee;
    // actual money received
    private BigDecimal amount;
}

