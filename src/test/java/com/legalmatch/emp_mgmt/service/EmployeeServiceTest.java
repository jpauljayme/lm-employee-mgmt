package com.legalmatch.emp_mgmt.service;

import com.legalmatch.emp_mgmt.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenGetEmployeeById_shouldReturnExistingEmployee(){
//        Address address = Address.builder()
//                .id(1L)
//                .addressDetails("In the middle of nothing and what the water gave me")
//                .build();
//
//        Contact contact = Contact.builder()
//                .id(1L)
//                .value("+123456")
//                .build();
//
//        Employee employee = Employee.builder()
//                .id(1L)
//                .firstName("JP")
//                .lastName("Jayme")
//                .address(address)
//                .contact(contact)
//                .build();
//
//
//        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
//
//
//        assertThat(employeeService.getEmployeeById(1L))
//                .isEqualTo(employee);
    }

    @Test
    public void whenGetAllEmployees_shouldReturnTwo(){
//        assertThat(employeeController.select())
    }
    }