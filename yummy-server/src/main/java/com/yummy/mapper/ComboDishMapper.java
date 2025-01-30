package com.yummy.mapper;

import com.yummy.entity.ComboDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface ComboDishMapper {

    List<Long> getIdByDishId(List<Long> dishIds);

    void insertBatch(List<ComboDish> comboDishes);

    void deleteByComboIds(List<Long> ids);

    @Delete("delete from combo_dish where combo_id =#{comboId}")
    void deleteByComboId(Long comboId);
}
