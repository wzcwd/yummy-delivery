package com.yummy.service;

import com.yummy.dto.EmployeeDTO;
import com.yummy.dto.EmployeeLoginDTO;
import com.yummy.dto.EmployeePageQueryDTO;
import com.yummy.entity.Employee;
import com.yummy.result.PageResult;

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


    /**
     * pageQuery
     * @param employeePageQueryDTO
     *
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
