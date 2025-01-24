package com.yummy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {
    // name of employee
    private String name;

    // page number
    private int page;

    private int pageSize;

}
