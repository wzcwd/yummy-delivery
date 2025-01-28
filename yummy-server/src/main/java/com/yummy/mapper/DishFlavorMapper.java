package com.yummy.mapper;

import com.yummy.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * batch insert
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * delete by dish ids
     * @param dishIds
     */
    void deleteByDishId(List<Long> dishIds);
}
