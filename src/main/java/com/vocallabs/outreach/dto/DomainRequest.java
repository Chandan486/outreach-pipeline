package com.vocallabs.outreach.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DomainRequest {

    @NotBlank(message = "Domain cannot be empty")
    private String domain;
}