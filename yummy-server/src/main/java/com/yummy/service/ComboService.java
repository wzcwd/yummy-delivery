package com.yummy.service;

import com.yummy.dto.ComboDTO;
import com.yummy.dto.ComboPageQueryDTO;
import com.yummy.entity.Combo;
import com.yummy.result.PageResult;
import com.yummy.vo.ComboVO;
import com.yummy.vo.DishItemVO;


import java.util.List;

public interface ComboService {
    void addCombo(ComboDTO comboDTO);

   /* PageResult comboPageQuery(ComboPageQueryDTO comboPageQueryDTO);

    ComboVO getComboById(Long id);

    void switchStatus(Long id);

    void update(ComboDTO comboDTO);

    void batchDelete(List<Long> ids);

    List<Combo> getList(Long categoryId);

    List<DishItemVO> getComboDishesById(Long id);
*/
}
