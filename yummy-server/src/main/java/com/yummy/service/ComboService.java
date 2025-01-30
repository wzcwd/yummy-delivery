package com.yummy.service;

import com.yummy.dto.ComboDTO;
import com.yummy.dto.ComboPageQueryDTO;
import com.yummy.result.PageResult;
import com.yummy.vo.ComboVO;


import java.util.List;

public interface ComboService {

    void addCombo(ComboDTO comboDTO);

    void updateStatus(Integer status, Long id);

    void batchDelete(List<Long> ids);

    PageResult pageQuery(ComboPageQueryDTO comboPageQueryDTO);

    void updateCombo(ComboDTO comboDTO);

    ComboVO getCombo(Long id);
}
