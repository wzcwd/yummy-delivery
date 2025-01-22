package com.yummy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    private Long id;

    // 1: dish category; 2: combo category
    private Integer type;

    // category name
    private String name;

    private Integer sort;

}
