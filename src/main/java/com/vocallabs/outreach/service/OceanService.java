package com.vocallabs.outreach.service;

import com.vocallabs.outreach.dto.CompanyResponse;
import java.util.List;

public interface OceanService {

    List<CompanyResponse> findSimilarCompanies(String domain);
}