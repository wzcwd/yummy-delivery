package com.yummy.controller.admin;

import com.yummy.dto.ComboDTO;
import com.yummy.result.Result;
import com.yummy.service.ComboService;
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
     * @param comboDTO
     * @return
     */
    @PostMapping
    public Result addCombo(@RequestBody ComboDTO comboDTO) {
        log.info("add new combo：{}", comboDTO);
        comboService.addCombo(comboDTO);
        return Result.success();
    }

    /**
     * enable or disable combo
     * @param status
     * @param id
     * @return Result
     */
    @PostMapping("/status/{status}")
    public Result switchStatus(@PathVariable Integer status, Long id) {
        log.info("switch combo status for id:{}", id );
        comboService.updateStatus(status, id);
        return Result.success();

    }
    /**
     * batch delete combos
     * @param ids
     * @return Result
     */
    @DeleteMapping
    public Result batchDeleteCombo(@RequestParam List<Long> ids) {
        log.info("batch delete combo ids:{}", ids);
        comboService.batchDelete(ids);
        return Result.success();
    }
}
