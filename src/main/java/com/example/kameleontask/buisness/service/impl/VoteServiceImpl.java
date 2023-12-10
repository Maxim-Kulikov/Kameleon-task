package com.example.kameleontask.buisness.service.impl;

import com.example.kameleontask.buisness.service.VoteService;
import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.data.entity.Vote;
import com.example.kameleontask.data.repository.VoteRepository;
import com.example.kameleontask.presentation.dto.response.VoteResp;
import com.example.kameleontask.presentation.dto.response.VoteWithQuoteResp;
import com.example.kameleontask.util.mapper.MainMapper;
import com.example.kameleontask.util.mapper.QuoteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.UTC;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final MainMapper mapper;
    private final QuoteMapper quoteMapper;

    @Override
    public List<VoteWithQuoteResp> findLastUserVotes(User user, Integer quantity) {
        return user.getVoteSet()
                .stream()
                .sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()))
                .limit(quantity)
                .map(vote -> mapper.toVoteWithQuoteResp(mapper.toVoteResp(vote), quoteMapper.toQuoteResp(vote.getQuote())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Vote> findVoteByUser(Quote quote, User user) {
        return quote.getVoteSet().stream()
                .filter(vote -> vote.getUser().getId().equals(user.getId()))
                .findFirst();
    }

    @Override
    public VoteResp saveVote(Quote quote, User user, Short isPositive) {
        Vote vote = voteRepository.save(Vote.builder()
                .createdAt(LocalDateTime.now(UTC))
                .isPositive(isPositive)
                .user(user)
                .quote(quote)
                .build());

        return mapper.toVoteResp(vote);
    }


}
