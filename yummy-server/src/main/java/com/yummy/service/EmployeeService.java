package com.yummy.service;

import com.yummy.dto.EmployeeDTO;
import com.yummy.dto.EmployeeLoginDTO;
import com.yummy.entity.Employee;

public interface EmployeeService {

    /**
     * employee login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * add employee
     * @param employeeDTO
     *
     */
    void addEmployee(EmployeeDTO employeeDTO);
}
