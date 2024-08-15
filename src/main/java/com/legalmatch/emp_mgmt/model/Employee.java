package com.legalmatch.emp_mgmt.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "emp_position")
    private String empPosition;

    @Column(name = "date_hired")
    private LocalDate dateHired;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private Set<Contact> contacts;

    public String getPrimaryAddress() {
        return this.addresses.stream()
                .filter(Address::getIsPrimary)
                .findFirst()
                .map(Address::getAddressDetails)
                .orElse("N/A");
    }

    public String getPrimaryContact() {
        return this.contacts.stream()
                .filter(Contact::getIsPrimary)
                .findFirst()
                .map(Contact::getContactDetails)
                .orElse("N/A");
    }

    public Long getAge(){
        return ChronoUnit.YEARS.between(this.birthDate, LocalDate.now());
    }

    public String getTenure(){
        if (this.dateHired == null) {
            return "N/A";
        }

        Period period = Period.between(this.dateHired,  LocalDate.now());
        int years = period.getYears();
        int months = period.getMonths();

        StringBuilder stringBuilder = new StringBuilder();
        if(years > 0){
            stringBuilder.append(years + "y");
        }

        if(months > 0){
            stringBuilder.append(" " + months + "m");
        }

        return stringBuilder.toString();
    }
}