package com.yummy.service.impl;

import com.yummy.constant.MessageConstant;
import com.yummy.constant.PasswordConstant;
import com.yummy.constant.StatusConstant;
import com.yummy.dto.EmployeeDTO;
import com.yummy.dto.EmployeeLoginDTO;
import com.yummy.entity.Employee;
import com.yummy.exception.AccountLockedException;
import com.yummy.exception.AccountNotFoundException;
import com.yummy.exception.PasswordErrorException;
import com.yummy.mapper.EmployeeMapper;
import com.yummy.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * Employee Login
     *
     * @param employeeLoginDTO
     * @return employee
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、check username in database
        Employee employee = employeeMapper.getByUsername(username);

        //2、handle exceptions（account not found, wrong password, account locked ）
        if (employee == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //compare the password after using  md5 to encrypt
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            // wrong password
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //check the status of the employee
        if (employee.getStatus() == StatusConstant.DISABLE) {
            //account locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;
    }

    /**
     * add employee by admin
     * @param employeeDTO
     * @return
     */
    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        // convert employeeDTO to Employee(entity)
        Employee employee = new Employee();
        // copyProperties from employeeDTO to employee
        BeanUtils.copyProperties(employeeDTO, employee);
        // set remaining attributes
        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        // TODO set create user
        employee.setCreateUser(10L);
        employee.setUpdateUser(10L);

        // write to database
        employeeMapper.insertEmployee(employee);


    }
}
