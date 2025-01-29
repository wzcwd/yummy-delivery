package com.yummy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yummy.constant.MessageConstant;
import com.yummy.constant.StatusConstant;
import com.yummy.dto.DishDTO;
import com.yummy.dto.DishPageQueryDTO;
import com.yummy.entity.Dish;
import com.yummy.entity.DishFlavor;
import com.yummy.exception.DeletionNotAllowedException;
import com.yummy.mapper.CategoryMapper;
import com.yummy.mapper.ComboDishMapper;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private ComboDishMapper comboDishMapper;
    @Autowired
    private CategoryMapper categoryMapper;

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
        if (flavors != null && !flavors.isEmpty()) {
            flavors.forEach(flavor -> {flavor.setDishId(dishId);});
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * dish page query
     * @param dishPageQueryDTO
     */
    @Override
    public PageResult dishPageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * batch delete dishes
     *
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDelete(List<Long> ids) {
        // can not delete  if the dish is available,
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // can not delete if the dish is associated with a combo
        List<Long> comboIds = comboDishMapper.getIdByDishId(ids);
        if (comboIds != null && !comboIds.isEmpty()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_RELATED_BY_COMBO);
        }
        // delete the dish
        dishMapper.deleteById(ids);
        // delete the dish_flavour associated with the dish
        dishFlavorMapper.deleteByDishIds(ids);


    }

    @Override
    public void enableOrDisable(Integer status, Long id) {
        dishMapper.updateStatus(status,id);
    }

    @Override
    public DishVO listDishById(Long id) {
        Dish dish = dishMapper.getById(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavorMapper.getByDishId(id));
        dishVO.setCategoryName(categoryMapper.getNameByCategoryID(dish.getCategoryId()));
        return dishVO;
    }

    @Override
    public List<Dish> getByCategoryId(Long categoryId) {
        List<Dish> dishes = dishMapper.getByCategoryId(categoryId);
        return dishes;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        // update dish table
        dishMapper.updateDish(dish);
        // update dish_value table
        // Delete the original data
        dishFlavorMapper.deleteByDishId(dishDTO.getId());
        // insert the updated data
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && !flavors.isEmpty()) {
            flavors.forEach(flavor -> {flavor.setDishId(dish.getId());});
        }
        dishFlavorMapper.insertBatch(flavors);
    }
}
