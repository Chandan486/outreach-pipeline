package com.vocallabs.outreach.service;

import com.vocallabs.outreach.dto.CompanyResponse;
import com.vocallabs.outreach.dto.ContactResponse;
import com.vocallabs.outreach.entity.*;
import com.vocallabs.outreach.repository.*;
import com.vocallabs.outreach.util.EmailUtil;
import com.vocallabs.outreach.util.RetryUtil;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class PipelineServiceImpl implements PipelineService {

    private final OceanService oceanService;
    private final ProspeoService prospeoService;
    private final EazyReachService eazyReachService;
    private final BrevoService brevoService;

    private final CompanyRepository companyRepository;
    private final ContactRepository contactRepository;
    private final EmailRepository emailRepository;
    private final OutreachLogRepository outreachLogRepository;

    private static final Logger log =
            LoggerFactory.getLogger(PipelineServiceImpl.class);

    @Override
    public void execute(String domain) {

        log.info("🚀 PIPELINE STARTED FOR: {}", domain);

        // STEP 1: Ocean - Similar companies
        List<CompanyResponse> companies =
                RetryUtil.execute(
                        () -> oceanService.findSimilarCompanies(domain),
                        3
                );

        if (companies == null) {
            log.error("❌ Ocean API failed after retries for domain: {}", domain);
            return;
        }

        log.info("✔ Companies Found: {}", companies.size());

        int totalContacts = 0;
        int totalEmails = 0;

        // STEP 2: Loop companies
        for (CompanyResponse c : companies) {

            Company company = new Company();
            company.setCompanyName(c.getCompanyName());
            company.setCompanyDomain(c.getCompanyDomain());
            company.setSeedDomain(domain);

            company = companyRepository.save(company);

            log.info("💾 Company saved: {} ({})",
                    company.getCompanyName(),
                    company.getCompanyDomain());

            // STEP 3: Prospeo - Contacts
            List<ContactResponse> contacts =
                    prospeoService.findDecisionMakers(company.getCompanyDomain());

            totalContacts += contacts.size();

            log.info("👥 Contacts found for {}: {}",
                    company.getCompanyDomain(),
                    contacts.size());

            for (ContactResponse contactRes : contacts) {

                Contact contact = new Contact();
                contact.setName(contactRes.getName());
                contact.setDesignation(contactRes.getDesignation());
                contact.setLinkedinUrl(contactRes.getLinkedinUrl());
                contact.setCompany(company);

                contact = contactRepository.save(contact);

                log.info("👤 Processing contact: {}",
                        contact.getName());

                // STEP 4: Email fetch
                log.info("📧 Fetching email for LinkedIn: {}",
                        contact.getLinkedinUrl());

                String emailStr =
                        eazyReachService.findWorkEmail(contact.getLinkedinUrl());

                if (emailStr == null || emailStr.isEmpty()) {
                    log.warn("❌ Email not found for: {}",
                            contact.getLinkedinUrl());
                    continue;
                }

                Email email = new Email();
                email.setEmail(emailStr);
                email.setVerified(true);
                email.setContact(contact);

                email = emailRepository.save(email);

                totalEmails++;

                log.info("✅ Email saved: {}", email.getEmail());

                // STEP 5: SAFETY CHECK (kept same, just logging replacement)
                log.info("━━━━━━━━━━━━━━━━━━━━━━");
                log.info("Company : {}", company.getCompanyDomain());
                log.info("Name    : {}", contact.getName());
                log.info("Email   : {}", email.getEmail());
                log.info("━━━━━━━━━━━━━━━━━━━━━━");

                log.info("⚠ Awaiting confirmation...");

                if (!confirmBeforeSend()) {
                    log.info("SKIPPED EMAIL for {}", email.getEmail());
                    continue;
                }

                // STEP 6: Email body
                String subject =
                        "Opportunity with " + company.getCompanyName();
                

                String body = EmailUtil.createEmail(
                        contact.getName(),
                        company.getCompanyName()
                );

                // STEP 7: Send email
                brevoService.sendEmail(
                        email.getEmail(),
                        subject,
                        body
                );

                // STEP 8: Log
                OutreachLog logEntity = new OutreachLog();
                logEntity.setEmail(email);
                logEntity.setSubject(subject);
                logEntity.setStatus("SENT");
                logEntity.setSentAt(LocalDateTime.now());

                outreachLogRepository.save(logEntity);

                log.info("📨 EMAIL SENT to {}", email.getEmail());
            }
        }

        // FINAL SUMMARY
        log.info("=======================");
        log.info("PIPELINE COMPLETE");
        log.info("Companies  : {}", companies.size());
        log.info("Contacts   : {}", totalContacts);
        log.info("Emails     : {}", totalEmails);
        log.info("=======================");
    }
    private boolean confirmBeforeSend() {

        System.out.println("\n⚠ FINAL CONFIRMATION REQUIRED");
        System.out.println("Emails are about to be sent.");

        Scanner sc = new Scanner(System.in);
        System.out.print("Type YES to continue: ");

        String input = sc.nextLine();

        return "YES".equalsIgnoreCase(input);
    }
}