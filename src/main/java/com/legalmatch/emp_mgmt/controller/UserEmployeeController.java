package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.service.EmployeeService;
import com.legalmatch.emp_mgmt.user.auth.UserEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@UserEndpoint
@RequiredArgsConstructor
@RequestMapping("/user/employees")
public class UserEmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee getEmployeeById(@Argument Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
