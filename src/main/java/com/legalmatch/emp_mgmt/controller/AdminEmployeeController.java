package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.admin.auth.AdminEndpoint;
import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//        employeeInput.addContact(ContactInput.builder()
//                        .isPrimary(false)
//                .build());
//        employeeInput.addAddress(AddressInput
//                .builder()
//                        .isPrimary(false)
//                .build());
        model.addAttribute("employeeInput", employeeInput);

        model.addAttribute("isAdmin", true);
        return "admin/employees";
    }

    @GetMapping("/showUpdateEmployeeForm")
    public String showUpdateEmployeeForm(Model model){
        EmployeeInput employeeInput = new EmployeeInput();
        model.addAttribute("employeeInput", employeeInput);

        model.addAttribute("isAdmin", true);
        return "admin/employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model){
        Employee employee = employeeGraphQLController.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "fragments :: updateEmployeeForm";  // Returning the modal content as a fragment
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateEmployee(@ModelAttribute EmployeeInput input, Model model){
        if(input.getId() == null){
            employeeGraphQLController.createEmployee(input);
        }else{
            employeeGraphQLController.updateEmployee(input);
        }

        // Reload the employee list
        model.addAttribute("employees", employeeGraphQLController.getAllEmployees());

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id, Model model){
        employeeGraphQLController.deleteEmployee(id);

        // Reload the employee list
        model.addAttribute("employees", employeeGraphQLController.getAllEmployees());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute EmployeeInput input, Model model){
        employeeGraphQLController.updateEmployee(input);

        // Reload the employee list
        model.addAttribute("employees", employeeGraphQLController.getAllEmployees());

        return "fragments :: employeeList";
    }
}