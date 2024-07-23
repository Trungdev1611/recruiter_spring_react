package com.recruiter.recruiter.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class VerifyToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Instant dateCreated;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id")
    private User user;
}
