package com.example.kameleontask.util.mapper;

import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.data.entity.Vote;
import com.example.kameleontask.presentation.dto.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MainMapper {
    UserResp toUserResp(User user);

    VoteResp toVoteResp(Vote vote);

    QuoteWithUserResp toQuoteWithUserResp(QuoteResp quoteResp, UserResp userResp);

    VoteWithQuoteResp toVoteWithQuoteResp(VoteResp voteResp, QuoteResp quoteResp);

}
