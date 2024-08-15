package com.legalmatch.emp_mgmt.input;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInput implements Input {
    private Long id;
    private String value;
    private Boolean isPrimary;
}
