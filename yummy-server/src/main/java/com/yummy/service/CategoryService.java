package com.yummy.service;

import com.yummy.dto.CategoryDTO;
import com.yummy.dto.CategoryPageQueryDTO;
import com.yummy.entity.Category;
import com.yummy.result.PageResult;
import java.util.List;

public interface CategoryService {

    /**
     * add category
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * page query
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * delete category by id
     * @param id
     */
    void deleteById(Long id);

    /**
     * update category
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * enable or disable category
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * search category by id
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
