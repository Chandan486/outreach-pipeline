package com.vocallabs.outreach.service;

import com.vocallabs.outreach.dto.CompanyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OceanServiceImpl implements OceanService {

    @Override
    public List<CompanyResponse> findSimilarCompanies(String domain) {

        return List.of(
                new CompanyResponse("Flipkart", "flipkart.com"),
                new CompanyResponse("Target", "target.com"),
                new CompanyResponse("Walmart", "walmart.com")
        );
    }
}