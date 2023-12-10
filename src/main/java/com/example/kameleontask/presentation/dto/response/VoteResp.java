package com.example.kameleontask.presentation.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class VoteResp {
    private Long id;
    private Short isPositive;
    private LocalDateTime createdAt;
}
