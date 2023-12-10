package com.example.kameleontask.presentation.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class QuoteWithUserResp {
    private QuoteResp quoteResp;
    private UserResp userResp;
}
