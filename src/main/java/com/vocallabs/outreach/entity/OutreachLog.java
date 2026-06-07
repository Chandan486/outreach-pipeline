package com.vocallabs.outreach.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "outreach_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutreachLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String status;

    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name = "email_id")
    private Email email;
}