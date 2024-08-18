package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.user.auth.UserEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@UserEndpoint
@RequiredArgsConstructor
@RequestMapping("/user/employees")
public class UserEmployeeController {
    private final EmployeeGraphQLController employeeGraphQLController;

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        return false;
    }

    @GetMapping("")
    public String showEmployees(Model model) {
        model.addAttribute("employees", employeeGraphQLController.getAllEmployees());

        EmployeeInput employeeInput = new EmployeeInput();
        model.addAttribute("employeeInput", employeeInput);

        return "employees";
    }

    @GetMapping("/{id}")
    public String showViewEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeGraphQLController.getEmployeeById(id);

        model.addAttribute("employee", employee);
        model.addAttribute("title", "View Employee Details");
        model.addAttribute("isAdmin", false);
        return "fragments :: createOrUpdateEmployeeForm";  // Returning the modal content as a fragment
    }
}
