package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.model.EmployeePage;
import com.legalmatch.emp_mgmt.user.auth.UserEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showPaginatedEmployees(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestHeader(value = "HX-Request", defaultValue = "false") boolean isHxRequest,
                                         Model model) {
        EmployeePage employeePage = employeeGraphQLController.getPaginatedEmployees(page, size);

        model.addAttribute("employeePage", employeePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());

        return isHxRequest ? "fragments :: employeeList" : "employees";
    }

    @GetMapping("/{id}")
    public String showViewEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeGraphQLController.getEmployeeById(id);

        model.addAttribute("employee", employee);
        model.addAttribute("title", "View Employee Details");
        model.addAttribute("isAdmin", false);
        return "fragments :: createOrUpdateEmployeeForm";
    }
}
