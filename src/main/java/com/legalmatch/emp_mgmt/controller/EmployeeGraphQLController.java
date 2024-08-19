package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.model.EmployeePage;
import com.legalmatch.emp_mgmt.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EmployeeGraphQLController {
    private final EmployeeService employeeService;

    @QueryMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @QueryMapping
    public EmployeePage getPaginatedEmployees(@Argument int page, @Argument int size){
        Page<Employee> employeePage = employeeService.getPaginatedEmployees(page, size);
        return EmployeePage.builder()
                .content(employeePage.getContent())
                .totalPages(employeePage.getTotalPages())
                .totalElements(employeePage.getTotalElements())
                .currentPage(employeePage.getNumber())
                .build();
    }

    @QueryMapping
    public Employee getEmployeeById(@Argument Long id){
        return employeeService.getEmployeeById(id).orElseThrow();
    }

    @MutationMapping
    public Employee createEmployee(@Argument EmployeeInput input){
        return employeeService.createEmployee(input);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument EmployeeInput input){
        return employeeService.updateEmployee(input);
    }

    @MutationMapping
    public boolean deleteEmployee(@Argument Long input){
        return employeeService.deleteEmployee(input);
    }
}
