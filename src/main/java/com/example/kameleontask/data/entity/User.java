package com.example.kameleontask.data.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Quote> quoteSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Vote> voteSet;
}
