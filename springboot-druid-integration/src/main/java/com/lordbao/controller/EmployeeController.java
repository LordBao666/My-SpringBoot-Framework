package com.lordbao.controller;

import com.lordbao.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/8/18 15:01
 * @Version 1.0
 */
@RestController
@RequestMapping("emp")
public class EmployeeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("list")
    public List<Employee> queryList(){
        String sql = "select * from t_emp";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }
}
