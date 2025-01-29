package com.yummy.service;

import com.yummy.dto.DishDTO;
import com.yummy.dto.DishPageQueryDTO;
import com.yummy.entity.Dish;
import com.yummy.result.PageResult;
import com.yummy.vo.DishVO;

import java.util.List;

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

    /**
     * batch delete dishes
     * @param ids
     */
    void batchDelete(List<Long> ids);

    /**
     * enable or disable dish
     * @param id
     */
    void enableOrDisable(Integer status, Long id);

    /**
     * List dish by id
     * @param id
     */
    DishVO listDishById(Long id);

    List<Dish> getByCategoryId(Long categoryId);

    /**
     * update dish with flavor
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);
}
