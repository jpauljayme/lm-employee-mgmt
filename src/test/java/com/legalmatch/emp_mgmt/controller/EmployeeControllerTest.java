package com.legalmatch.emp_mgmt.controller;

import com.legalmatch.emp_mgmt.model.*;
import com.legalmatch.emp_mgmt.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Set;

import static org.mockito.BDDMockito.given;

@GraphQlTest(UserEmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeeById() {

        var address = Set.of(Address.builder()
                .isPrimary(true)
                .addressDetails("Cebu")
                .build());

        var contact = Set.of(Contact.builder()
                .isPrimary(true)
                .contactDetails("09055716384")
                .build());

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .addresses(address)
                .contacts(contact)
                .build();

        given(employeeService.getEmployeeById(1L)).willReturn(employee);

        // Act & Assert
        graphQlTester.documentName("employee-query")
                .variable("id", 1L)
                .execute()
                .path("getEmployeeById")
                .entity(Employee.class)
                .matches(emp -> emp.getFirstName().equals("John") && emp.getLastName().equals("Doe"));
    }

//    @Test
//    public void testGetAllEmployees() {
//        Employee employee1 = Employee.builder()
//                .id(1L)
//                .firstName("John")
//                .lastName("Doe")
//                .birthdate(LocalDate.of(1998,11,9))
//                .gender(Gender.Male)
//                .maritalStatus(MaritalStatus.Single)
//                .empPosition("Programmer")
//                .dateHired(LocalDate.now())
//                .address(Address.builder()
//                        .id(1L)
//                        .isPrimary(true)
//                        .addressDetails("Cebu")
//                        .build())
//                .contact(Contact.builder()
//                        .id(1L)
//                        .isPrimary(true)
//                        .value("09055716384")
//                        .build())
//                .build();
//
//        Employee employee2 = Employee.builder()
//                .id(2L)
//                .firstName("Hayley")
//                .lastName("Williams")
//                .birthdate(LocalDate.of(1998,11,9))
//                .gender(Gender.Female)
//                .maritalStatus(MaritalStatus.Divorced)
//                .empPosition("Singer")
//                .dateHired(LocalDate.now())
//                .address(Address.builder()
//                        .id(2L)
//                        .isPrimary(true)
//                        .addressDetails("Nashville")
//                        .build())
//                .contact(Contact.builder()
//                        .id(2L)
//                        .isPrimary(true)
//                        .value("09055123488")
//                        .build())
//                .build();
//
//        given(employeeService.select()).willReturn(List.of(employee1, employee2));
//
//        // Act & Assert
//        graphQlTester.documentName("employees-query")
//                .execute()
//                .path("select")
//                .entityList(Employee.class)
//                .hasSize(2)
//                .matches(employees -> employees.stream().anyMatch(e -> e.getFirstName().equals("John")))
//                .matches(employees -> employees.stream().anyMatch(e -> e.getFirstName().equals("Jane")));
//    }
}