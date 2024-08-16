package com.legalmatch.emp_mgmt.input;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressInput implements Input{
    private Long id;
    private String addressDetails;
    private Boolean isPrimary;
}
