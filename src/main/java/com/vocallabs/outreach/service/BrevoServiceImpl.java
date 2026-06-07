package com.vocallabs.outreach.service;

import org.springframework.stereotype.Service;

@Service
public class BrevoServiceImpl implements BrevoService {

    @Override
    public void sendEmail(String to, String subject, String body) {

        System.out.println("Email sent to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}