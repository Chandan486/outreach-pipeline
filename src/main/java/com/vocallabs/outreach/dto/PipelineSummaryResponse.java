package com.vocallabs.outreach.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PipelineSummaryResponse {

    private Integer companiesFound;

    private Integer contactsFound;

    private Integer emailsVerified;

    private Integer emailsSent;
}