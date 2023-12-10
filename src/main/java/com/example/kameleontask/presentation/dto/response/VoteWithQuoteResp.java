package com.example.kameleontask.presentation.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class VoteWithQuoteResp {
    private VoteResp voteResp;
    private QuoteResp quoteResp;
}
