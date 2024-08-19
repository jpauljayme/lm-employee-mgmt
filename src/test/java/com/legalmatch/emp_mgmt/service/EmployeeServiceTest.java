package com.legalmatch.emp_mgmt.service;

import com.legalmatch.emp_mgmt.input.AddressInput;
import com.legalmatch.emp_mgmt.input.ContactInput;
import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.Address;
import com.legalmatch.emp_mgmt.model.Contact;
import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    public void whenGetEmployeeById_shouldReturnExistingEmployee() {
        Address address = Address.builder()
                .id(1L)
                .addressDetails("In the middle of nothing and what the water gave me")
                .build();

        Contact contact = Contact.builder()
                .id(1L)
                .contactDetails("+123456")
                .build();

        Optional<Employee> employee = Optional.of(Employee.builder()
                .id(1L)
                .firstName("JP")
                .lastName("Jayme")
                .addresses(List.of(address))
                .contacts(List.of(contact))
                .build());


        given(employeeRepository.findById(1L)).willReturn(employee);

        assertThat(employeeService.getEmployeeById(1L)).isEqualTo(employee);
    }

    @Test
    public void whenGetAllEmployees_shouldReturnTwo() {
        Address address = Address.builder()
                .id(1L)
                .addressDetails("In the middle of nothing and what the water gave me")
                .build();

        Contact contact = Contact.builder()
                .id(1L)
                .contactDetails("+123456")
                .build();

        var employee1 = Employee.builder()
                .id(1L)
                .firstName("JP")
                .lastName("Jayme")
                .addresses(List.of(address))
                .contacts(List.of(contact))
                .build();

        var employee2 = Employee.builder()
                .id(1L)
                .firstName("Fyodor")
                .lastName("Dostoevsky")
                .addresses(List.of(address))
                .contacts(List.of(contact))
                .build();

        var employees = List.of(employee1, employee2);

        given(employeeRepository.findAll())
                .willReturn(employees);

        assertThat(employeeService.getAllEmployees())
                .isEqualTo(employees);

    }

    @Test
    public void whenCreateEmployee() {
        var addressInput = AddressInput.builder()
                .id(1L)
                .addressDetails("In the middle of nothing and what the water gave me")
                .build();

        var contactInput = ContactInput.builder()
                .id(1L)
                .contactDetails("+123456")
                .build();

        var employeeInput = EmployeeInput.builder()
                .id(1L)
                .firstName("JP")
                .lastName("Jayme")
                .addresses(List.of(addressInput))
                .contacts(List.of(contactInput))
                .build();

        var address = Address.builder()
                .id(1L)
                .addressDetails("In the middle of nothing and what the water gave me")
                .build();

        var contact = Contact.builder()
                .id(1L)
                .contactDetails("+123456")
                .build();

        var employee = Employee.builder()
                .id(1L)
                .firstName("JP")
                .lastName("Jayme")
                .addresses(List.of(address))
                .contacts(List.of(contact))
                .build();

        given(employeeRepository.save(any(Employee.class)))
                .willReturn(employee);

        final Employee savedEmployee = employeeService.createEmployee(employeeInput);

        assertThat(savedEmployee)
                .isEqualTo(employee);
    }

    @Test
    public void whenDeleteEmployee() {

        Long employeeId = 1L;

        given(employeeRepository.existsById(employeeId))
                .willReturn(true);

        final boolean result = employeeService.deleteEmployee(employeeId);

        assertThat(result)
                .isEqualTo(true);

        verify(employeeRepository, times(1))
                .deleteById(employeeId);
    }
}