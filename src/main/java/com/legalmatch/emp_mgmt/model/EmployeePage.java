package com.legalmatch.emp_mgmt.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EmployeePage {
    private List<Employee> content;
    private long totalElements;
    private int totalPages;
    private int currentPage;

}
