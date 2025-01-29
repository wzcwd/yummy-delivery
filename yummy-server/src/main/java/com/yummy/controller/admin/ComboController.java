package com.yummy.controller.admin;

import com.yummy.dto.ComboDTO;
import com.yummy.result.PageResult;
import com.yummy.result.Result;
import com.yummy.service.ComboService;
import com.yummy.vo.ComboVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/combo")
@Slf4j
public class ComboController {

    @Autowired
    private ComboService comboService;

    /**
     * add new combo
     * @param comboDTO
     * @return
     */
    @PostMapping
    public Result addCombo(@RequestBody ComboDTO comboDTO) {
        log.info("add new combo：{}", comboDTO);
        comboService.addCombo(comboDTO);
        return Result.success();
    }

  /*  *//**
     * 套餐条件分页查询
     * @param setmealPageDTO
     * @return
     *//*
    @GetMapping("/page")
    public Result<PageResult> getPageList(SetmealPageDTO setmealPageDTO){
        log.info("条件分页查询：{}", setmealPageDTO);
        PageResult pageResult = setmealService.getPageList(setmealPageDTO);
        return Result.success(pageResult);
    }

    *//**
     * 根据id查询套餐
     * @param id
     * @return
     *//*
    @GetMapping("/{id}")
    public Result<ComboVO> getSetmealById(@PathVariable Integer id){
        log.info("要查询的套餐id：{}", id);
        ComboVO setmealVO = setmealService.getSetmealById(id);
        return Result.success(setmealVO);
    }

    *//**
     * 根据id起售停售套餐
     * @param id
     * @return
     *//*
    @PutMapping("/status/{id}")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result onOff(@PathVariable Integer id){
        log.info("套餐id:{}", id);
        setmealService.onOff(id);
        return Result.success();
    }

    *//**
     * 修改套餐
     * @param setmealDTO
     * @return
     *//*
    @PutMapping
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改后的套餐信息：{}", setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    *//**
     * 批量删除套餐
     * @param ids
     * @return
     *//*
    @DeleteMapping
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result deleteBatch(@RequestParam List<Integer> ids){
        log.info("批量删除套餐的套餐id集合：{}", ids);
        setmealService.deleteBatch(ids);
        return Result.success();
    }
*/
}
