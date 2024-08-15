package com.legalmatch.emp_mgmt.input;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdInput implements Input{
    private Long id;
}
