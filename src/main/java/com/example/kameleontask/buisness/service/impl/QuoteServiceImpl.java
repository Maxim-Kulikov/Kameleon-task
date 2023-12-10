package com.example.kameleontask.buisness.service.impl;

import com.example.kameleontask.buisness.service.QuoteService;
import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.data.repository.QuoteRepository;
import com.example.kameleontask.presentation.dto.request.SaveQuoteReq;
import com.example.kameleontask.presentation.dto.request.SortQuotesReq;
import com.example.kameleontask.presentation.dto.request.UpdateQuoteReq;
import com.example.kameleontask.presentation.dto.response.QuoteResp;
import com.example.kameleontask.presentation.dto.response.QuoteWithUserResp;
import com.example.kameleontask.util.exception.conflict.QuoteNotFoundException;
import com.example.kameleontask.util.mapper.MainMapper;
import com.example.kameleontask.util.mapper.QuoteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.UTC;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final MainMapper mapper;
    private final QuoteMapper quoteMapper;

    @Override
    public List<QuoteWithUserResp> findFirstNQuotesByVotes(SortQuotesReq sortQuotesReq) {
        return quoteRepository
                .findTopQuotesOrderByPositiveVotes(sortQuotesReq.getIsPositive())
                .stream()
                .limit(sortQuotesReq.getQuantity())
                .map(quote -> mapper.toQuoteWithUserResp(quoteMapper.toQuoteResp(quote), mapper.toUserResp(quote.getUser())))
                .collect(Collectors.toList());
    }

    @Override
    public QuoteResp findRandomQuote() {
        return quoteMapper.toQuoteResp(quoteRepository.findRandomQuote().orElseThrow(QuoteNotFoundException::new));
    }

    @Override
    public QuoteResp saveQuote(User user, SaveQuoteReq saveQuoteReq) {
        Quote quote = quoteRepository.save(
                Quote.builder()
                        .content(saveQuoteReq.getContent())
                        .createdAt(LocalDateTime.now(UTC))
                        .user(user)
                        .build());

        return quoteMapper.toQuoteResp(quote);
    }

    @Override
    public QuoteResp updateQuote(UpdateQuoteReq updateQuoteReq, Long id) {
        Quote quote = findQuoteOrElseThrow(id);
        quote.setContent(updateQuoteReq.getContent());
        quote.setUpdatedAt(LocalDateTime.now(UTC));

        quoteRepository.save(quote);

        return quoteMapper.toQuoteResp(quote);
    }

    @Override
    public void deleteQuote(Long id) {
        quoteRepository.delete(findQuoteOrElseThrow(id));
    }

    @Override
    public Quote findQuoteOrElseThrow(Long id) {
        return quoteRepository.findById(id).orElseThrow(QuoteNotFoundException::new);
    }

}
