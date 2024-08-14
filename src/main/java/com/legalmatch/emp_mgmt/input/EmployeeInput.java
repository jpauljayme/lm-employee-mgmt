package com.legalmatch.emp_mgmt.input;

import com.legalmatch.emp_mgmt.model.Gender;
import com.legalmatch.emp_mgmt.model.MaritalStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeInput implements Input{

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthdate;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String empPosition;
    private LocalDate dateHired;
    private Set<AddressInput> addresses;
    private Set<ContactInput> contacts;
}
