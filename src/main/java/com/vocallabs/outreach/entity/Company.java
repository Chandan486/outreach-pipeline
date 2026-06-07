package com.vocallabs.outreach.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seedDomain;

    private String companyName;

    @Column(unique = true)
    private String companyDomain;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "company",
               cascade = CascadeType.ALL)
    private List<Contact> contacts;
}