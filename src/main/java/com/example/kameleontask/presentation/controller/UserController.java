package com.example.kameleontask.presentation.controller;

import com.example.kameleontask.buisness.facade.MainFacade;
import com.example.kameleontask.presentation.dto.request.SaveUserReq;
import com.example.kameleontask.presentation.dto.response.UserResp;
import com.example.kameleontask.presentation.dto.response.VoteWithQuoteResp;
import com.example.kameleontask.util.logging.annotation.LoggableHttpRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final MainFacade mainFacade;

    @LoggableHttpRequest(description = "Save user")
    @PostMapping("/save")
    public UserResp saveUser(@RequestBody @Valid SaveUserReq saveUserReq) {
        return mainFacade.saveUser(saveUserReq);
    }

    @LoggableHttpRequest(description = "Find last votes by user votes")
    @GetMapping("/{id}/votes/last")
    public List<VoteWithQuoteResp> findLastUserVotes(@PathVariable @Positive(message = "id") Long id,
                                                     @RequestParam @Positive(message = "quantity") Integer quantity) {
        return mainFacade.findLastUserVotes(id, quantity);
    }

}
