package com.vocallabs.outreach.repository;

import com.vocallabs.outreach.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository
        extends JpaRepository<Company, Long> {
}