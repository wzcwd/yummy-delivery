package com.yummy.mapper;

import com.github.pagehelper.Page;
import com.yummy.annotation.AutoFill;
import com.yummy.dto.ComboPageQueryDTO;
import com.yummy.entity.Combo;
import com.yummy.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ComboMapper {

    @Select("select count(id) from combo where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    @AutoFill(value = OperationType.INSERT)
    void insertCombo(Combo combo);

    @Update("update combo set status = #{status} where id=#{id}")
    void updateStatus(Integer status, Long id);

    @Select("select status from combo where id=#{id}")
    Integer getStatus(Long id);

    void deleteByIds(List<Long> ids);

    Page<Combo> pageQuery(ComboPageQueryDTO comboPageQueryDTO);

    @AutoFill(value = OperationType.UPDATE)
    void update(Combo combo);

    @Select("select * from combo where id=#{id}")
    Combo getById(Long id);
}
