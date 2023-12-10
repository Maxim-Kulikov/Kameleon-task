package com.example.kameleontask.buisness.facade;

import com.example.kameleontask.presentation.dto.request.*;
import com.example.kameleontask.presentation.dto.response.*;

import java.util.List;

public interface MainFacade {
    UserResp saveUser(SaveUserReq saveUserReq);

    List<QuoteWithUserResp> findFirstNQuotesByVotes(SortQuotesReq sortQuotesReq);

    QuoteResp findRandomQuote();

    QuoteResp saveQuote(SaveQuoteReq saveQuoteReq);

    QuoteResp updateQuote(UpdateQuoteReq updateQuoteReq, Long id);

    void deleteQuote(Long id);

    List<VoteWithQuoteResp> findLastUserVotes(Long idUser, Integer quantity);

    VoteResp saveVote(SaveVoteReq saveVoteReq);
}
