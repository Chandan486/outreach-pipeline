# Automated Outreach Pipeline

## Overview

Automated Outreach Pipeline is a Spring Boot application that automates B2B lead generation and email outreach.

The system takes a seed company domain, discovers similar companies, identifies decision-makers, finds their professional email addresses, generates personalized outreach emails, and sends them automatically.

The entire workflow is logged and stored in PostgreSQL for tracking and reporting.

---

## Problem Statement

Manual lead generation and outreach require significant time and effort. This project automates the process by integrating multiple external services to:

- Discover potential target companies
- Find key decision-makers
- Retrieve verified business emails
- Generate personalized outreach content
- Send emails automatically
- Track outreach history

---

## Features

### Company Discovery
- Accepts a seed company domain
- Finds similar companies using external APIs

### Contact Discovery
- Retrieves decision-makers from target companies
- Collects professional information

### Email Discovery
- Fetches business email addresses
- Validates available contact information

### Email Generation
- Creates personalized outreach messages
- Supports dynamic content generation

### Email Delivery
- Sends emails through Brevo
- Tracks delivery status

### Database Storage
- Stores companies
- Stores contacts
- Stores email addresses
- Stores outreach history

### Monitoring
- Application logging
- API call tracking
- Error handling and retries

---

# Architecture

```text
                    +----------------+
                    | User Request   |
                    +-------+--------+
                            |
                            v
                  +------------------+
                  | Spring Boot API  |
                  +--------+---------+
                           |
        ------------------------------------------
        |                |              |         |
        v                v              v         v

   Ocean API      Prospeo API    EazyReach API  Brevo API

        |                |              |         |
        ------------------------------------------
                           |
                           v

                  PostgreSQL Database
```

---

# Technology Stack

## Backend

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Web
- OpenFeign
- Lombok

## Database

- PostgreSQL

## Documentation

- Swagger/OpenAPI

## Build Tool

- Maven

---

# Project Structure

```text
src
└── main
    └── java
        └── com.vocallabs.outreach

            ├── controller
            ├── service
            ├── repository
            ├── entity
            ├── dto
            ├── config
            ├── client
            └── util
```

---

# Workflow

## Step 1

User submits a company domain.

Example:

```json
{
  "domain": "amazon.com"
}
```

## Step 2

Ocean API returns similar companies.

Example:

- Walmart
- Target
- Costco

## Step 3

Prospeo API identifies decision-makers.

Example:

- CEO
- CTO
- VP Engineering

## Step 4

EazyReach API finds professional email addresses.

Example:

```text
john@company.com
sarah@company.com
```

## Step 5

Personalized outreach emails are generated.

## Step 6

Brevo sends the emails.

## Step 7

Results are stored in PostgreSQL.

---

# Database Entities

## Company

| Field | Type |
|---------|---------|
| id | Long |
| name | String |
| domain | String |
| seedDomain | String |

---

## Contact

| Field | Type |
|---------|---------|
| id | Long |
| name | String |
| designation | String |
| linkedinUrl | String |

---

## Email

| Field | Type |
|---------|---------|
| id | Long |
| email | String |
| verified | Boolean |

---

## Outreach Log

| Field | Type |
|---------|---------|
| id | Long |
| subject | String |
| status | String |
| sentAt | Timestamp |

---

# API Endpoints

## Health Check

```http
GET /api/health
```

Response:

```json
{
  "status": "Application Running"
}
```

---

## Execute Outreach Pipeline

```http
POST /api/outreach/run
```

Request:

```json
{
  "domain": "amazon.com"
}
```

Response:

```json
{
  "message": "Pipeline Executed Successfully"
}
```

---

# Installation

## Clone Repository

```bash
git clone https://github.com/Chandan486/automated-outreach-pipeline.git
```

## Navigate to Project

```bash
cd automated-outreach-pipeline
```

## Configure PostgreSQL

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/outreach_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Build Application

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

---

# Swagger Documentation

After starting the application:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Logging

The application logs:

- Company discovery
- Contact discovery
- Email retrieval
- Email delivery status
- Pipeline completion summary

Example:

```text
INFO  Similar companies found : 5
INFO  Contacts discovered : 10
INFO  Emails found : 8
INFO  Emails sent : 8
INFO  Pipeline completed successfully
```

---

# Future Enhancements

- AI-generated email personalization
- Bulk outreach campaigns
- Campaign analytics dashboard
- Scheduled outreach jobs
- Docker support
- Kubernetes deployment
- User authentication and authorization

---

# Author

**Chandrakanta Pradhan**

Java Full Stack Developer

### Skills

- Java
- Spring Boot
- Hibernate
- SQL
- REST APIs
- HTML
- CSS
- JavaScript

---
