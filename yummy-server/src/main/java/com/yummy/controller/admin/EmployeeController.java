package com.yummy.controller.admin;

import com.yummy.constant.JwtClaimsConstant;
import com.yummy.dto.EmployeeDTO;
import com.yummy.dto.EmployeeLoginDTO;
import com.yummy.dto.EmployeePageQueryDTO;
import com.yummy.dto.PasswordEditDTO;
import com.yummy.entity.Employee;
import com.yummy.properties.JwtProperties;
import com.yummy.result.PageResult;
import com.yummy.result.Result;
import com.yummy.service.EmployeeService;
import com.yummy.utils.JwtUtil;
import com.yummy.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * employee management
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Login
     *
     * @param employeeLoginDTO
     * @return Result
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("staff login：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        // generate jwt token if login successful
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        // builder design pattern for building an object
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * Logout
     *
     * @return Result
     */
    @PostMapping("/logout")
    public Result<Void> logout() {
        return Result.success();
    }

    /**
     * Add employee
     * @param employeeDTO
     *
     * @return Result
     */
    @PostMapping
    public Result<Void> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("add employee: {}", employeeDTO);
        employeeService.addEmployee(employeeDTO);
        return Result.success();
    }

    /**
     * get Employee By Page
     * @param employeePageQueryDTO
     *
     * @return Result
     */
    @GetMapping("/page")
    public Result<PageResult> getEmployeeByPage(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("getEmployeeByPage: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);

    }

    /**
     * enable or disable user account
     * @param status
     * @param id
     * @return Result
     */
    @PostMapping("/status/{status}")
    public Result<Void> changeStatus(@PathVariable Integer status, Long id) {
        log.info("changeStatus: {},{}", status, id);
        employeeService.changeStatus(status, id);
        return Result.success();
    }

    /**
     * search employee by id
     * @param id
     *
     * @return Result
     */
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("getById: {}", id);
        Employee employee = employeeService.getEmployee(id);
        return Result.success(employee);
    }

    /**
     * update employee info
     * @param employeeDTO
     *
     * @return Result
     */
    @PutMapping
    public Result<Void> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("update Employee info: {}", employeeDTO);
        employeeService.updateInfo(employeeDTO);
        return Result.success();
    }

    /**
     * update password
     * @param passwordEditDTO
     */
    @PutMapping("/editPassword")
    public Result<Void> editPassword(@RequestBody PasswordEditDTO passwordEditDTO) {
        log.info("update password: {}", passwordEditDTO);
        employeeService.updatePassword(passwordEditDTO);
        return Result.success();
    }










}
