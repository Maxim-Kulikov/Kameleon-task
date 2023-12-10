package com.example.kameleontask.presentation.controller;

import com.example.kameleontask.buisness.facade.MainFacade;
import com.example.kameleontask.presentation.dto.request.SaveVoteReq;
import com.example.kameleontask.presentation.dto.response.VoteResp;
import com.example.kameleontask.util.logging.annotation.LoggableHttpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

    private final MainFacade mainFacade;

    @LoggableHttpRequest(description = "Save user")
    @PostMapping("/save")
    public VoteResp saveVote(@RequestBody @Valid SaveVoteReq saveVoteReq) {
        return mainFacade.saveVote(saveVoteReq);
    }

}
