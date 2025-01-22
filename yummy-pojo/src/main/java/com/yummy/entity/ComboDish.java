package com.yummy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Relationship between dish and combo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboDish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long comboId;

    private Long dishId;

    //dish name
    private String name;

    //dish price
    private BigDecimal price;

    private Integer copies;
}
