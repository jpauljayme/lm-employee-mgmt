package com.legalmatch.emp_mgmt.service;

import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.repository.AddressRepository;
import com.legalmatch.emp_mgmt.repository.ContactRepository;
import com.legalmatch.emp_mgmt.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
}
