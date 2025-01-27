package com.yummy.service;

import com.yummy.dto.DishDTO;
import com.yummy.dto.DishPageQueryDTO;
import com.yummy.result.PageResult;

public interface DishService {

    /**
     * add new dish
     * @param dishDTO
     */
    public void addWithFlavor(DishDTO  dishDTO);

    /**
     * dish page query
     * @param dishPageQueryDTO
     */
    PageResult dishPageQuery(DishPageQueryDTO dishPageQueryDTO);
}
