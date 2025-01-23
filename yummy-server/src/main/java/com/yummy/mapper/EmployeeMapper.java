package com.yummy.mapper;

import com.yummy.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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



}
