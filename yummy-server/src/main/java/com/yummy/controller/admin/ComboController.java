package com.yummy.controller.admin;

import com.yummy.dto.ComboDTO;
import com.yummy.dto.ComboPageQueryDTO;
import com.yummy.result.PageResult;
import com.yummy.result.Result;
import com.yummy.service.ComboService;
import com.yummy.vo.ComboVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
     *
     * @param comboDTO:
     * @return Result
     */
    @PostMapping
    public Result<Void> addCombo(@RequestBody ComboDTO comboDTO) {
        log.info("add new combo：{}", comboDTO);
        comboService.addCombo(comboDTO);
        return Result.success();
    }

    /**
     * enable or disable combo
     * @param status:
     * @param id:
     * @return Result
     */
    @PostMapping("/status/{status}")
    public Result<Void> switchStatus(@PathVariable Integer status, Long id) {
        log.info("switch combo status for id:{}", id );
        comboService.updateStatus(status, id);
        return Result.success();

    }
    /**
     * batch delete combos
     * @param ids:
     * @return Result
     */
    @DeleteMapping
    public Result<Void> batchDeleteCombo(@RequestParam List<Long> ids) {
        log.info("batch delete combo ids:{}", ids);
        comboService.batchDelete(ids);
        return Result.success();
    }

    /**
     * combo page query
     * @param comboPageQueryDTO:
     * @return Result
     */
    @GetMapping("/page")
    public Result<PageResult> comboPageQuery(ComboPageQueryDTO comboPageQueryDTO) {
        log.info("pageQuery comboPageQueryDTO:{}", comboPageQueryDTO);
        PageResult pageResult = comboService.pageQuery(comboPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * update combo
     * @param comboDTO:
     * @return Result
     */
    @PutMapping
    public Result<Void> updateCombo(@RequestBody ComboDTO comboDTO) {
        log.info("update combo comboDTO:{}", comboDTO);
        comboService.updateCombo(comboDTO);
        return Result.success();
    }

    /**
     * get combo by comboId
     * @param id: combo id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result<ComboVO> getComboById(@PathVariable Long id) {
        log.info("get combo by id:{}", id);
        ComboVO comboVO = comboService.getCombo(id);
        return Result.success(comboVO);
    }







}
