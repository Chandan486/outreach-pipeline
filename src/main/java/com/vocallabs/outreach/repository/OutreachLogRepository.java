package com.vocallabs.outreach.repository;

import com.vocallabs.outreach.entity.OutreachLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutreachLogRepository
        extends JpaRepository<OutreachLog, Long> {
}