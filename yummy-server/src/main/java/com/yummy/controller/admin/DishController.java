package com.yummy.controller.admin;

import com.yummy.dto.DishDTO;
import com.yummy.dto.DishPageQueryDTO;
import com.yummy.result.PageResult;
import com.yummy.result.Result;
import com.yummy.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Dish Management
 */
@RestController
@Slf4j
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO) {
        log.info("addDish:{}", dishDTO);
        dishService.addWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result getDishPage(DishPageQueryDTO dishPageQueryDTO) {
        log.info("getDishPage:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.dishPageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

}



