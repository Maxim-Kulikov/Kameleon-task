package com.example.kameleontask.buisness.service;

import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.data.entity.Vote;
import com.example.kameleontask.presentation.dto.response.VoteResp;
import com.example.kameleontask.presentation.dto.response.VoteWithQuoteResp;

import java.util.List;
import java.util.Optional;

public interface VoteService {
    List<VoteWithQuoteResp> findLastUserVotes(User user, Integer quantity);

    Optional<Vote> findVoteByUser(Quote quote, User user);

    VoteResp saveVote(Quote quote, User user, Short isPositive);
}
