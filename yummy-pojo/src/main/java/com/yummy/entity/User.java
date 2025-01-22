package com.yummy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // unique id of wechat users
    private String openid;

    private String name;

    private String tel;

    //0:female; 1:male
    private String gender;

    //id number 身份证号
    private String idNumber;

    private String photo;

    private LocalDateTime createTime;
}
