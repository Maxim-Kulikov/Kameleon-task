package com.example.kameleontask.buisness.facade.impl;

import com.example.kameleontask.buisness.facade.MainFacade;
import com.example.kameleontask.buisness.service.QuoteService;
import com.example.kameleontask.buisness.service.UserService;
import com.example.kameleontask.buisness.service.VoteService;
import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.presentation.dto.request.*;
import com.example.kameleontask.presentation.dto.response.*;
import com.example.kameleontask.util.exception.conflict.UserAlreadyVotedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainFacadeImpl implements MainFacade {

    private final UserService userService;
    private final QuoteService quoteService;
    private final VoteService voteService;

    @Override
    public UserResp saveUser(SaveUserReq saveUserReq) {
        return userService.saveUser(saveUserReq);
    }

    @Override
    public List<QuoteWithUserResp> findFirstNQuotesByVotes(SortQuotesReq sortQuotesReq) {
        return quoteService.findFirstNQuotesByVotes(sortQuotesReq);
    }

    @Override
    public QuoteResp findRandomQuote() {
        return quoteService.findRandomQuote();
    }

    @Override
    public QuoteResp saveQuote(SaveQuoteReq saveQuoteReq) {
        User user = userService.findUserOrElseThrow(saveQuoteReq.getIdUser());
        return quoteService.saveQuote(user, saveQuoteReq);
    }

    @Override
    public QuoteResp updateQuote(UpdateQuoteReq updateQuoteReq, Long id) {
        return quoteService.updateQuote(updateQuoteReq, id);
    }

    @Override
    public void deleteQuote(Long id) {
        quoteService.deleteQuote(id);
    }

    @Override
    public List<VoteWithQuoteResp> findLastUserVotes(Long idUser, Integer quantity) {
        User user = userService.findUserOrElseThrow(idUser);
        return voteService.findLastUserVotes(user, quantity);
    }

    @Override
    public VoteResp saveVote(SaveVoteReq saveVoteReq) {
        Quote quote = quoteService.findQuoteOrElseThrow(saveVoteReq.getIdQuote());
        User user = userService.findUserOrElseThrow(saveVoteReq.getIdUser());

        if (voteService.findVoteByUser(quote, user).isPresent()) {
            throw new UserAlreadyVotedException();
        }

        return voteService.saveVote(quote, user, saveVoteReq.getIsPositive());
    }


}
