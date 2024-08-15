package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.admin.auth.AdminEndpoint;
import com.legalmatch.emp_mgmt.input.AddressInput;
import com.legalmatch.emp_mgmt.input.ContactInput;
import com.legalmatch.emp_mgmt.input.EmployeeCreationDto;
import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/employees")
@AdminEndpoint
public class AdminEmployeeController {

    private final EmployeeGraphQLController employeeGraphQLController;

    @GetMapping("")
    public String showEmployees(Model model){
        model.addAttribute("employees", employeeGraphQLController.getAllEmployees());

        EmployeeInput employeeInput = new EmployeeInput();
        employeeInput.addContact(new ContactInput(1L, "sdqwe", true));
        employeeInput.addContact(new ContactInput());
        employeeInput.addAddress(new AddressInput(1L, "existing...", true));
//        employeeInput.addAddress(new AddressInput());
        model.addAttribute("employeeInput", employeeInput);
        return "admin/employees";
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeGraphQLController.getEmployeeById(id);
    }

    @PostMapping("/createEmployee")
    public String createEmployee(@ModelAttribute EmployeeInput input, Model model){
        log.debug("At createEmployee : {}", input.toString());
        employeeGraphQLController.createEmployee(input);
        return "redirect:/employees";
    }
}
