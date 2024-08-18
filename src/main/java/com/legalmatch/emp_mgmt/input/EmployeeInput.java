package com.legalmatch.emp_mgmt.input;

import com.legalmatch.emp_mgmt.model.Gender;
import com.legalmatch.emp_mgmt.model.MaritalStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInput implements Input{
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String empPosition;
    private LocalDate dateHired;

    @Builder.Default
    private List<AddressInput> addresses = new ArrayList<>();

    @Builder.Default
    private List<ContactInput> contacts = new ArrayList<>();

    public void addAddress(AddressInput addressInput){
        this.addresses.add(addressInput);
    }

    public void addContact(ContactInput contactInput){
        this.contacts.add(contactInput);
    }
}
