package com.vocallabs.outreach.repository;

import com.vocallabs.outreach.entity.OutreachRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutreachRepository
        extends JpaRepository<OutreachRecord, Long> {
}