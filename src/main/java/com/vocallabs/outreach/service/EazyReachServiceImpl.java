package com.vocallabs.outreach.service;

import org.springframework.stereotype.Service;

@Service
public class EazyReachServiceImpl implements EazyReachService {

    @Override
    public String findWorkEmail(String linkedinUrl) {

        return "john.doe@company.com";
    }
}