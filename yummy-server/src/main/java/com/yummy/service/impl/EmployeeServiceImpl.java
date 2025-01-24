package com.yummy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yummy.constant.MessageConstant;
import com.yummy.constant.PasswordConstant;
import com.yummy.constant.StatusConstant;
import com.yummy.context.BaseContext;
import com.yummy.dto.EmployeeDTO;
import com.yummy.dto.EmployeeLoginDTO;
import com.yummy.dto.EmployeePageQueryDTO;
import com.yummy.entity.Employee;
import com.yummy.exception.AccountLockedException;
import com.yummy.exception.AccountNotFoundException;
import com.yummy.exception.PasswordErrorException;
import com.yummy.mapper.EmployeeMapper;
import com.yummy.result.PageResult;
import com.yummy.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

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
        // get the id of current user from ThreadLocal
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());
        // write to database
        employeeMapper.insertEmployee(employee);
    }


    /**
     * show  employee in page
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        // select * from employee limit 0, 10
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        // get total rows
        long total = page.getTotal();
        // get the list of all rows in current page
        List<Employee> records = page.getResult();
        return new PageResult(total,records);
    }

    /**
     * enable or disable user account
     * @param status, id
     *
     */
    @Override
    public void changeStatus(Integer status, Long id) {

        Employee employee = Employee.builder()
                .id(id)
                .status(status)
                .build();
        // use dynamic sql for code reuse
        employeeMapper.update(employee);
    }

    /**
     * search employee by id
     * @param id
     *
     * @return Employee
     */
    @Override
    public Employee getEmployee(Long id) {
        Employee employee = employeeMapper.listEmployee(id);
        // for security concerns, set password ****
        employee.setPassword("******");
        return employee;
    }

    /**
     * update employee info
     * @param employeeDTO
     *
     * @return
     */
    @Override
    public void updateInfo(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeMapper.update(employee);
    }
}

