package com.yummy.mapper;

import com.yummy.annotation.AutoFill;
import com.yummy.entity.Dish;
import com.yummy.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * count the dish number by category id
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * add new dish
     * @param dish
     * @return
     */
    @AutoFill(value = OperationType.INSERT)
    void insertDish(Dish dish);

}
