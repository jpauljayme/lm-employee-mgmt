package com.legalmatch.emp_mgmt.service;

import com.legalmatch.emp_mgmt.input.AddressInput;
import com.legalmatch.emp_mgmt.input.ContactInput;
import com.legalmatch.emp_mgmt.input.EmployeeInput;
import com.legalmatch.emp_mgmt.model.Address;
import com.legalmatch.emp_mgmt.model.Contact;
import com.legalmatch.emp_mgmt.model.Employee;
import com.legalmatch.emp_mgmt.repository.AddressRepository;
import com.legalmatch.emp_mgmt.repository.ContactRepository;
import com.legalmatch.emp_mgmt.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;


    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(EmployeeInput input) {

        List<Contact> contacts = input.getContacts().stream()
                .map(this::mapToContactEntity)
                .toList();

        List<Address> addresses = input.getAddresses().stream()
                .map(this::mapToAddressEntity)
                .toList();

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

        // Clear and add updated addresses
        existingEmployee.getAddresses().clear();
        input.getAddresses().forEach(addressInput -> {
            Address address = Address.builder()
                    .id(addressInput.getId())
                    .addressDetails(addressInput.getAddressDetails())
                    .isPrimary(addressInput.getIsPrimary())
                    .employee(existingEmployee)
                    .build();
            existingEmployee.getAddresses().add(address);
        });

        // Clear and add updated contacts
        existingEmployee.getContacts().clear();
        input.getContacts().forEach(contactInput -> {
            Contact contact = Contact.builder()
                    .id(contactInput.getId())
                    .contactDetails(contactInput.getContactDetails())
                    .isPrimary(contactInput.getIsPrimary())
                    .employee(existingEmployee)
                    .build();
            existingEmployee.getContacts().add(contact);
        });

        return employeeRepository.save(existingEmployee);
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Address mapToAddressEntity(AddressInput addressInput) {
        return Address.builder()
                .addressDetails(addressInput.getAddressDetails())
                .isPrimary(ObjectUtils.isNotEmpty(addressInput.getIsPrimary()) && addressInput.getIsPrimary())
                .build();
    }

    private Contact mapToContactEntity(ContactInput contactInput) {
        return Contact.builder()
                .contactDetails(contactInput.getContactDetails())
                .isPrimary(ObjectUtils.isNotEmpty(contactInput.getIsPrimary()) && contactInput.getIsPrimary())
                .build();
    }

    public Page<Employee> getPaginatedEmployees(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page,size));
    }
}
