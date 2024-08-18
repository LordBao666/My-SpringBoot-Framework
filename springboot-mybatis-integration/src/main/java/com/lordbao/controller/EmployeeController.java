package com.lordbao.controller;

import com.lordbao.pojo.Employee;
import com.lordbao.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/8/18 16:36
 * @Version 1.0
 */
@RestController
@RequestMapping("emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("list")
    public List<Employee> queryAll(){
        return employeeService.queryAll();
    }

    @PostMapping("insert")
    public int insert(@RequestBody Employee employee){
        return employeeService.insert(employee);
    }
}
