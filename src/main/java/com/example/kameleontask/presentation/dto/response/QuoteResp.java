package com.example.kameleontask.presentation.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class QuoteResp {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long positiveVotesQuantity;
    private Long negativeVotesQuantity;
}
