package com.vocallabs.outreach.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponse {

    private String email;

    private Boolean verified;
}