package com.yummy.service;

import com.yummy.dto.ComboDTO;


import java.util.List;

public interface ComboService {
    void addCombo(ComboDTO comboDTO);

    void updateStatus(Integer status, Long id);

    void batchDelete(List<Long> ids);
}
