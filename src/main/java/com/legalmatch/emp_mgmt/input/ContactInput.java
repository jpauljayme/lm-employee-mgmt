package com.legalmatch.emp_mgmt.input;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactInput implements Input {

    private String value;
    private Boolean isPrimary;
}
