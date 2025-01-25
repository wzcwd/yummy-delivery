package com.yummy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComboMapper {

    /**
     * count the combo number by category id
     * @param id
     * @return
     */
    @Select("select count(id) from combo where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

}
