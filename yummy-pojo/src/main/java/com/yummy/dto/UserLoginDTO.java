package com.yummy.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Client user login
 */
@Data
public class UserLoginDTO implements Serializable {

    private String code;

}
