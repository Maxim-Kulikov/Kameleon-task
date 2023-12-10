package com.example.kameleontask.util.mapper.impl;

import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.data.entity.Vote;
import com.example.kameleontask.presentation.dto.response.QuoteResp;
import com.example.kameleontask.util.mapper.QuoteMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class QuoteMapperImpl implements QuoteMapper {
    @Override
    public QuoteResp toQuoteResp(Quote quote) {
        QuoteResp quoteResp = QuoteResp.builder()
                .id(quote.getId())
                .content(quote.getContent())
                .createdAt(quote.getCreatedAt())
                .updatedAt(quote.getUpdatedAt())
                .negativeVotesQuantity(0L)
                .positiveVotesQuantity(0L)
                .build();

        if (quote.getVoteSet() != null) {
            countAndSetPositiveAndNegativeVotes(quoteResp, quote.getVoteSet());
        }

        return quoteResp;
    }

    private void countAndSetPositiveAndNegativeVotes(QuoteResp quoteResp, Set<Vote> voteSet) {
        long positiveVotes = 0;
        long negativeVotes = 0;

        for (Vote vote : voteSet) {
            if (vote.getIsPositive() == 1) {
                positiveVotes++;
            } else {
                negativeVotes++;
            }
        }

        quoteResp.setPositiveVotesQuantity(positiveVotes);
        quoteResp.setNegativeVotesQuantity(negativeVotes);
    }
}
