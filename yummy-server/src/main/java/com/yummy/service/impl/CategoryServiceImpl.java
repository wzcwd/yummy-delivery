package com.yummy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yummy.constant.MessageConstant;
import com.yummy.constant.StatusConstant;
import com.yummy.context.BaseContext;
import com.yummy.dto.CategoryDTO;
import com.yummy.dto.CategoryPageQueryDTO;
import com.yummy.entity.Category;
import com.yummy.exception.DeletionNotAllowedException;
import com.yummy.mapper.CategoryMapper;
import com.yummy.mapper.DishMapper;
import com.yummy.mapper.ComboMapper;
import com.yummy.result.PageResult;
import com.yummy.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Category service
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private ComboMapper comboMapper;

    /**
     * add new category
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        // set default status as disable
        category.setStatus(StatusConstant.DISABLE);

        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(category);
    }

    /**
     * page query
     * @param categoryPageQueryDTO
     * @return
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * delete category by id
     * @param id
     */
    public void deleteById(Long id) {
        // check if the category is associated with dish, if so new exception
        Integer count = dishMapper.countByCategoryId(id);
        if(count > 0){
            // category is associated with dish, can not delete
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        //  // check if the category is associated with combo, if so new exception
        count = comboMapper.countByCategoryId(id);
        if(count > 0){
            // category is associated with combo, can not delete
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_COMBO);
        }

        categoryMapper.deleteById(id);
    }

    /**
     * update category
     * @param categoryDTO
     */
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.update(category);
    }

    /**
     * enable or disable category
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    /**
     * search category by type
     * @param type
     * @return
     */
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }
}
