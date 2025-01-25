package com.yummy.dto;

import com.yummy.entity.ComboDish;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ComboDTO implements Serializable {

    private Long id;

    private Long categoryId;

    private String name;

    private BigDecimal price;

    //0: disable  1:enable
    private Integer status;

    private String description;

    private String image;

    // relation between combo and dish
    private List<ComboDish> comboDishes = new ArrayList<>();

}
