package com.example.kameleontask.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "QUOTES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    @ToString.Exclude
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quote", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Vote> voteSet;

}
