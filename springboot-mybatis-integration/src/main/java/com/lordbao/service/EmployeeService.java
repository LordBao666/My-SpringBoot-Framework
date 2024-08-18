package com.lordbao.service;

import com.lordbao.pojo.Employee;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/8/18 16:21
 * @Version 1.0
 */
public interface EmployeeService {
    int insert(Employee employee);
    List<Employee> queryAll();
}
