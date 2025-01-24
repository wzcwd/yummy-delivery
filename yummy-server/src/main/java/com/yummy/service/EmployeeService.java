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

    /**
     * enable or disable user account
     * @param status
     * @param id
     *
     */
    void changeStatus(Integer status, Long id);

    /**
     * search employee by id
     * @param id
     *
     * @return Employee
     */
    Employee getEmployee(Long id);

    /**
     * update employee info
     * @param employeeDTO
     *
     * @return
     */
    void updateInfo(EmployeeDTO employeeDTO);
}
