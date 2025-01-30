package com.yummy.vo;

import com.yummy.entity.ComboDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboVO implements Serializable {

    // combo id
    private Long id;

    private Long categoryId;

    //combo name
    private String name;

    private BigDecimal price;

    //0:disable;  1:enable
    private Integer status;

    private String description;

    private String image;

    private LocalDateTime updateTime;

    private String categoryName;

    private List<ComboDish> comboDishes = new ArrayList<>();
}
