package com.vocallabs.outreach.service;

import com.vocallabs.outreach.dto.ContactResponse;
import java.util.List;

public interface ProspeoService {

    List<ContactResponse> findDecisionMakers(String companyDomain);
}