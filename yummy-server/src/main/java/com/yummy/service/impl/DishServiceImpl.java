package com.yummy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yummy.dto.DishDTO;
import com.yummy.dto.DishPageQueryDTO;
import com.yummy.entity.Dish;
import com.yummy.entity.DishFlavor;
import com.yummy.mapper.DishFlavorMapper;
import com.yummy.mapper.DishMapper;
import com.yummy.result.PageResult;
import com.yummy.service.DishService;
import com.yummy.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWithFlavor(DishDTO dishDTO) {
        // insert into dish table
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insertDish(dish);

        // get dishId using " useGeneratedKeys = true"
        Long dishId = dish.getId();

        // insert into dish_flavor table
        // flavour is List<DishFlavor> means a dish can have multiple flavors
        List<DishFlavor> flavors = dishDTO.getFlavors();
        // note that flavor can be null
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(flavor -> {flavor.setDishId(dishId);});
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public PageResult dishPageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
