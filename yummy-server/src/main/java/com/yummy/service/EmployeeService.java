package com.yummy.service;

import com.yummy.dto.EmployeeLoginDTO;
import com.yummy.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

}
