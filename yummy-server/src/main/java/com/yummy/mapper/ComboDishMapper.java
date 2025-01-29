package com.yummy.mapper;

import com.yummy.entity.ComboDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface ComboDishMapper {

    List<Long> getIdByDishId(List<Long> dishIds);

    void insertBatch(List<ComboDish> comboDishes);
}
