package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.admin.auth.AdminEndpoint;
import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/employees")
@AdminEndpoint
public class AdminEmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @QueryMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @QueryMapping
    public Employee getEmployeeById(@Argument Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/create")
    @MutationMapping
    public Employee createEmployee(@Argument EmployeeInput input){
        return employeeService.createEmployee(input);
    }

    @PostMapping("/update")
    @MutationMapping
    public Employee updateEmployee(@Argument EmployeeInput input){
        return employeeService.updateEmployee(input);
    }

    @DeleteMapping("/update")
    @MutationMapping
    public boolean deleteEmployee(@Argument EmployeeInput input){
        return employeeService.deleteEmployee(input);
    }
}
