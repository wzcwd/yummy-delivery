package com.yummy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface ComboDishMapper {

    List<Long> getIdByDishId(List<Long> dishIds);
}
