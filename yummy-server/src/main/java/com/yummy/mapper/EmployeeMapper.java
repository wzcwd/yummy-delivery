package com.yummy.mapper;

import com.github.pagehelper.Page;
import com.yummy.dto.EmployeePageQueryDTO;
import com.yummy.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    /**
     * search employee based on username
     * @param username
     * @return Employee
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);


    /**
     * insert employee
     * @param employee
     *
     */
    @Insert("insert into employee (name, username, password, tel, gender, id_number, status, create_time, update_time, create_user, update_user) " +
            "values(#{name},#{username},#{password}, #{tel}, #{gender}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insertEmployee(Employee employee);

    /**
     * page query
     * @param employeePageQueryDTO
     *
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * enable or disable user account
     * @param employee
     *
     */
    void update(Employee employee);

    @Select("select  * from employee where  id =#{id}")
    Employee listEmployee(Long id);
}
