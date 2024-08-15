package com.legalmatch.emp_mgmt.service;

import com.legalmatch.emp_mgmt.input.AddressInput;
import com.legalmatch.emp_mgmt.input.ContactInput;
import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.*;
import com.legalmatch.emp_mgmt.repository.AddressRepository;
import com.legalmatch.emp_mgmt.repository.ContactRepository;
import com.legalmatch.emp_mgmt.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
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

    public Employee createEmployee(EmployeeInput input) {

        Set<Contact> contacts = input.getContacts().stream()
                .map(this::mapToContactEntity)
                .collect(Collectors.toSet());

        Set<Address> addresses = input.getAddresses().stream()
                .map(this::mapToAddressEntity)
                .collect(Collectors.toSet());

        Employee employee = Employee.builder()
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .middleName(input.getMiddleName())
                .birthDate(input.getBirthDate())
                .gender(input.getGender())
                .maritalStatus(input.getMaritalStatus())
                .empPosition(input.getEmpPosition())
                .dateHired(input.getDateHired())
                .addresses(addresses)
                .contacts(contacts)
                .build();

        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(EmployeeInput input) {
        Employee existingEmployee = employeeRepository.findById(input.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Update basic fields
        existingEmployee.setFirstName(input.getFirstName());
        existingEmployee.setLastName(input.getLastName());
        existingEmployee.setMiddleName(input.getMiddleName());
        existingEmployee.setBirthDate(input.getBirthDate());
        existingEmployee.setGender(input.getGender());
        existingEmployee.setMaritalStatus(input.getMaritalStatus());
        existingEmployee.setEmpPosition(input.getEmpPosition());
        existingEmployee.setDateHired(input.getDateHired());

        // Save or update addresses
        input.getAddresses().forEach(addressInput -> {
            Address address = Address.builder()
                    .id(addressInput.getId())  // Use id to determine if it’s an update
                    .addressDetails(addressInput.getValue())
                    .isPrimary(addressInput.getIsPrimary())
                    .employee(existingEmployee)
                    .build();
            addressRepository.save(address);
        });

        // Save or update contacts
        input.getContacts().forEach(contactInput -> {
            Contact contact = Contact.builder()
                    .id(contactInput.getId())  // Use id to determine if it’s an update
                    .contactDetails(contactInput.getValue())
                    .isPrimary(contactInput.getIsPrimary())
                    .employee(existingEmployee)
                    .build();
            contactRepository.save(contact);
        });

        return employeeRepository.save(existingEmployee);
    }

    public boolean deleteEmployee(EmployeeInput input) {
        final long id = input.getId();
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Address mapToAddressEntity(AddressInput addressInput) {
        return Address.builder()
                .addressDetails(addressInput.getValue())
                .isPrimary(addressInput.getIsPrimary())
                .build();
    }

    private Contact mapToContactEntity(ContactInput contactInput) {
        return Contact.builder()
                .contactDetails(contactInput.getValue())
                .isPrimary(contactInput.getIsPrimary())
                .build();
    }
}
