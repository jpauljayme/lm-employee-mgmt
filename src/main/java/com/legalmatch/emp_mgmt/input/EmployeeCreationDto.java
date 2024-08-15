package com.legalmatch.emp_mgmt.input;

import com.legalmatch.emp_mgmt.model.Contact;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
public class EmployeeCreationDto {
    private Set<AddressInput> addresses = new HashSet<>();
    private Set<ContactInput> contacts = new HashSet<>();

    public void addAddress(AddressInput addressInput){
        this.addresses.add(addressInput);
    }

    public void addContact(ContactInput contactInput){
        this.contacts.add(contactInput);
    }
}
