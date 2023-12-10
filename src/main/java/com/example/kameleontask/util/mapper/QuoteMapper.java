package com.example.kameleontask.util.mapper;

import com.example.kameleontask.data.entity.Quote;
import com.example.kameleontask.presentation.dto.response.QuoteResp;
import org.mapstruct.Mapper;

public interface QuoteMapper {
    QuoteResp toQuoteResp(Quote quote);
}
