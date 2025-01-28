package com.yummy.service;

import com.yummy.dto.DishDTO;
import com.yummy.dto.DishPageQueryDTO;
import com.yummy.result.PageResult;
import org.apache.ibatis.annotations.Update;

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
}
