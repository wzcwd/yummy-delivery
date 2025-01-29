package com.yummy.controller.admin;

import com.yummy.dto.DishDTO;
import com.yummy.dto.DishPageQueryDTO;
import com.yummy.entity.Dish;
import com.yummy.result.PageResult;
import com.yummy.result.Result;
import com.yummy.service.DishService;
import com.yummy.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dish Management
 */
@RestController
@Slf4j
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     *add new dish
     * @param dishDTO)
     */
    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO) {
        log.info("addDish:{}", dishDTO);
        dishService.addWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     *Page query
     * @param dishPageQueryDTO
     */
    @GetMapping("/page")
    public Result<PageResult> getDishPage(DishPageQueryDTO dishPageQueryDTO) {
        log.info("getDishPage:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.dishPageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * batch delete dishes
     * @param ids
     */
    @DeleteMapping
    public Result deleteDish(@RequestParam List<Long> ids) {
        log.info("deleteDish:{}", ids);
        dishService.batchDelete(ids);
        return Result.success();
    }

    /**
     * enable or disable dish by id
     * @param status
     * @return Result
     */
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, Long id) {
        log.info("switch dish status，{}", id);
        dishService.enableOrDisable(status,id);
        return Result.success();
    }

    /**
     * get dish by id with flavor
     * @param id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result<DishVO> getDishById(@PathVariable Long id) {
        log.info("getDishById:{}", id);
        DishVO dishVO = dishService.listDishById(id);
        return Result.success(dishVO);
    }
    /**
     * get dishes by category id
     * @param categoryId
     * @return Result
     */
    @GetMapping("/list")
    public Result<List<Dish>> getDishByCategoryId(Long categoryId) {
        log.info("getDishByCategoryId:{}", categoryId);
        List<Dish> dishes = dishService.getByCategoryId(categoryId);
        return  Result.success(dishes);
    }

    /**
     * update dish
     * @param dishDTO
     * @return Result
     */
    @PutMapping
    public Result updateDish(@RequestBody DishDTO dishDTO) {
        log.info("updateDish:{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }








}




