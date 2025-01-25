package com.yummy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ComboPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;

    private Integer categoryId;

    //0:disable;  1: enable
    private Integer status;

}
