package com.example.kameleontask.presentation.controller;

import com.example.kameleontask.buisness.facade.MainFacade;
import com.example.kameleontask.presentation.dto.request.SaveQuoteReq;
import com.example.kameleontask.presentation.dto.request.UpdateQuoteReq;
import com.example.kameleontask.presentation.dto.request.SortQuotesReq;
import com.example.kameleontask.presentation.dto.response.QuoteResp;
import com.example.kameleontask.presentation.dto.response.QuoteWithUserResp;
import com.example.kameleontask.util.logging.annotation.LoggableHttpRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final MainFacade mainFacade;

    @LoggableHttpRequest(description = "Find first N quotes by negative or positive votes")
    @PostMapping("/top")
    public List<QuoteWithUserResp> findFirstNQuotesByVotes(@RequestBody @Valid SortQuotesReq sortQuotesReq) {
        return mainFacade.findFirstNQuotesByVotes(sortQuotesReq);
    }

    @LoggableHttpRequest(description = "Find random quote")
    @GetMapping("/random")
    public QuoteResp findRandomQuote() {
        return mainFacade.findRandomQuote();
    }

    @LoggableHttpRequest(description = "Save quote")
    @PostMapping("/save")
    public QuoteResp saveQuote(@RequestBody @Valid SaveQuoteReq saveQuoteReq) {
        return mainFacade.saveQuote(saveQuoteReq);
    }

    @LoggableHttpRequest(description = "Update quote")
    @PatchMapping("/update/{id}")
    public QuoteResp updateQuote(@RequestBody @Valid UpdateQuoteReq updateQuoteReq,
                               @PathVariable @Positive(message = "id") Long id) {
        return mainFacade.updateQuote(updateQuoteReq, id);
    }

    @LoggableHttpRequest(description = "Delete quote")
    @DeleteMapping("/delete/{id}")
    public void deleteQuote(@PathVariable @Positive(message = "id") Long id) {
        mainFacade.deleteQuote(id);
    }

}
