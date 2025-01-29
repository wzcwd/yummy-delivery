package com.yummy.dto;

import com.yummy.entity.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdersDTO implements Serializable {

    private Long id;

    // order number
    private String number;

    //订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
    private Integer status;

    private Long userId;

    private Long addressBookId;

    private LocalDateTime orderTime;

    private LocalDateTime checkoutTime;

    private Integer payMethod;

    // actual money received
    private BigDecimal amount;

    private String remark;

    private String userName;

    private String tel;

    private String address;

    private String receiver;

    private List<OrderDetail> orderDetails;

}
