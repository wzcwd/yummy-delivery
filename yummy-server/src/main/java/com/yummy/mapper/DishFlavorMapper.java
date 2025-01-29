package com.yummy.mapper;

import com.yummy.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    void deleteByDishIds(List<Long> dishIds);

    @Delete("delete from dish_flavor where dish_id =#{dishId}")
    void deleteByDishId(Long dishId);
    /**
     * list flavor by dish id
     * @param dishId
     */
    @Select("select * from dish_flavor where dish_id=#{dishId}")
    List<DishFlavor> getByDishId(Long dishId);

}
