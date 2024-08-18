package com.lordbao.mapper;

import com.lordbao.pojo.Employee;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/8/18 16:22
 * @Version 1.0
 */
public interface EmployeeMapper {
    List<Employee> queryAll();
    int insert(Employee employee);
}
