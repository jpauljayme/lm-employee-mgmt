package com.legalmatch.emp_mgmt.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Column(name = "address_details")
    private String addressDetails;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}