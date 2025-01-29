package com.yummy.mapper;

import com.yummy.annotation.AutoFill;
import com.yummy.entity.Combo;
import com.yummy.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComboMapper {

    /**
     * count the combo number by category id
     * @param id
     *
     */
    @Select("select count(id) from combo where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * add new combo
     * @param combo
     *
     */
    @AutoFill(value = OperationType.INSERT)
    void insertCombo(Combo combo);
}
