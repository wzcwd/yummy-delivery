package com.yummy.service;

import com.yummy.dto.DishDTO;

public interface DishService {

    /**
     * add new dish
     * @param dishDTO
     */
    public void addWithFlavor(DishDTO  dishDTO);

}
