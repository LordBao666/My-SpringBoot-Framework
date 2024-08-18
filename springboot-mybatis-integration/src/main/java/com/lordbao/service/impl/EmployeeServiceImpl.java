package com.lordbao.service.impl;

import com.lordbao.mapper.EmployeeMapper;
import com.lordbao.pojo.Employee;
import com.lordbao.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/8/18 16:34
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Transactional
    @Override
    public int insert(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public List<Employee> queryAll() {
        return employeeMapper.queryAll();
    }
}
