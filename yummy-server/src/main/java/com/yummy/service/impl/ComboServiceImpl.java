package com.yummy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yummy.constant.MessageConstant;
import com.yummy.constant.StatusConstant;
import com.yummy.dto.ComboDTO;
import com.yummy.dto.ComboPageQueryDTO;
import com.yummy.entity.Combo;
import com.yummy.entity.ComboDish;
import com.yummy.exception.DeletionNotAllowedException;
import com.yummy.mapper.ComboDishMapper;
import com.yummy.mapper.ComboMapper;
import com.yummy.result.PageResult;
import com.yummy.service.ComboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    private ComboMapper comboMapper;
    @Autowired
    private ComboDishMapper comboDishMapper;

    /**
     * add new combo
     *
     * @param comboDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCombo(ComboDTO comboDTO) {
        Combo combo = new Combo();
        BeanUtils.copyProperties(comboDTO, combo);
        comboMapper.insertCombo(combo);
        // insert dishes in combo_dish table
        Long comboId = combo.getId();
        List<ComboDish> comboDishes = comboDTO.getComboDishes();
        if (comboDishes != null && !comboDishes.isEmpty()) {
            comboDishes.forEach(comboDish -> comboDish.setComboId(comboId));
            comboDishMapper.insertBatch(comboDishes);
        }
    }

    /**
     * update combo status
     *
     * @param status
     * @param id
     */
    @Override
    public void updateStatus(Integer status, Long id) {
        comboMapper.updateStatus(status, id);
    }

    /**
     * batch delete combos
     *
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDelete(List<Long> ids) {
        // we can not delete valid combo(status == 1)
        for (Long id : ids) {
            if (Objects.equals(comboMapper.getStatus(id), StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.COMBO_ON_SALE);
            }
        }
        comboMapper.deleteByIds(ids);
        // we should also delete the related combo_dish
        comboDishMapper.deleteByComboIds(ids);
    }

    /**
     * combo page query
     *
     * @param comboPageQueryDTO
     * @return PageResult
     */
    @Override
    public PageResult pageQuery(ComboPageQueryDTO comboPageQueryDTO) {
        PageHelper.startPage(comboPageQueryDTO.getPage(), comboPageQueryDTO.getPageSize());
        // can search by combo name, category id or status
        Page<Combo> page = comboMapper.pageQuery(comboPageQueryDTO);
        long total = page.getTotal();
        List<Combo> combos = page.getResult();
        return new PageResult(total, combos);
    }

    /**
     * update combo
     *
     * @param comboDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCombo(ComboDTO comboDTO) {
        Combo combo = new Combo();
        BeanUtils.copyProperties(comboDTO, combo);
        comboMapper.update(combo);
        // delete the related data in combo_dish
        comboDishMapper.deleteByComboId(combo.getId());
        // insert new data
        List<ComboDish> comboDishes = comboDTO.getComboDishes();
        if (comboDishes != null && !comboDishes.isEmpty()) {
            comboDishes.forEach(comboDish ->
                    comboDish.setComboId(combo.getId()));
        }
        comboDishMapper.insertBatch(comboDTO.getComboDishes());
    }
}
