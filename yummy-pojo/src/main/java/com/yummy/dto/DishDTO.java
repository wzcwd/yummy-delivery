package com.yummy.dto;

import com.yummy.entity.DishFlavor;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {

    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    // path of the image
    private String image;
    private String description;
    //0: out of stock; 1: available
    private Integer status;

    private List<DishFlavor> flavors = new ArrayList<>();

}
