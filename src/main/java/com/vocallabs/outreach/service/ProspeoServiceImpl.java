package com.vocallabs.outreach.service;

import com.vocallabs.outreach.dto.ContactResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspeoServiceImpl implements ProspeoService {

    @Override
    public List<ContactResponse> findDecisionMakers(String companyDomain) {

        return List.of(
                new ContactResponse(
                        "John Doe",
                        "VP Engineering",
                        "https://linkedin.com/in/johndoe"
                ),
                new ContactResponse(
                        "Jane Smith",
                        "CTO",
                        "https://linkedin.com/in/janesmith"
                )
        );
    }
}