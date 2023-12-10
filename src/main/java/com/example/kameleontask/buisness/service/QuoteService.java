package com.example.kameleontask.buisness.service;

import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.presentation.dto.request.SaveQuoteReq;
import com.example.kameleontask.presentation.dto.request.SortQuotesReq;
import com.example.kameleontask.presentation.dto.request.UpdateQuoteReq;
import com.example.kameleontask.presentation.dto.response.QuoteResp;
import com.example.kameleontask.presentation.dto.response.QuoteWithUserResp;

import java.util.List;

public interface QuoteService {
    List<QuoteWithUserResp> findFirstNQuotesByVotes(SortQuotesReq sortQuotesReq);

    QuoteResp findRandomQuote();

    QuoteResp saveQuote(User user, SaveQuoteReq saveQuoteReq);

    QuoteResp updateQuote(UpdateQuoteReq updateQuoteReq, Long id);

    void deleteQuote(Long id);

    Quote findQuoteOrElseThrow(Long id);
}
