package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.admin.auth.AdminEndpoint;
import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.model.EmployeePage;
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

    private static final String STRING_EMPLOYEES = "employees";

    private final EmployeeGraphQLController employeeGraphQLController;

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        return true;
    }

    @GetMapping("")
    public String showPaginatedEmployees(  @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size,
                                           @RequestHeader(value = "HX-Request", defaultValue = "false") boolean isHxRequest,
            Model model) {
        EmployeePage employeePage = employeeGraphQLController.getPaginatedEmployees(page, size);

        model.addAttribute("employeePage", employeePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());

        EmployeeInput employeeInput = new EmployeeInput();
        model.addAttribute("employeeInput", employeeInput);

        return isHxRequest ? "fragments :: employeeList" : STRING_EMPLOYEES;
    }

    @GetMapping("/{id}")
    public String showUpdateEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeGraphQLController.getEmployeeById(id);

        model.addAttribute("employee", employee);
        model.addAttribute("title", "Edit Employee Form");

        return "fragments :: createOrUpdateEmployeeForm";  // Returning the modal content as a fragment
    }

    @GetMapping("/create")
    public String showCreateEmployeeForm(Model model) {
        EmployeeInput employee = EmployeeInput.builder().build();

        model.addAttribute("employee", employee);

        model.addAttribute("title", "Create Employee Form");
        return "fragments :: createOrUpdateEmployeeForm";  // Returning the modal content as a fragment
    }


    @PostMapping("/saveOrUpdate")
    public ResponseEntity<Object> saveOrUpdateEmployee(@ModelAttribute EmployeeInput input, Model model) {
        if (input.getId() == null) {
            employeeGraphQLController.createEmployee(input);
        } else {
            employeeGraphQLController.updateEmployee(input);
        }

        model.addAttribute(STRING_EMPLOYEES, employeeGraphQLController.getAllEmployees());

        return ResponseEntity.ok()
                .header("HX-Redirect", "/admin/employees")
                .build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id, Model model) {
        employeeGraphQLController.deleteEmployee(id);

        model.addAttribute(STRING_EMPLOYEES, employeeGraphQLController.getAllEmployees());

        return ResponseEntity.ok().build();
    }
}