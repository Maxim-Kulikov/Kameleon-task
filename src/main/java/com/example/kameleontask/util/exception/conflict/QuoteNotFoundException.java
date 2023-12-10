package com.example.kameleontask.util.exception.conflict;

import static com.example.kameleontask.util.exception.code.ExceptionCode.QUOTE_NOT_FOUND;

public class QuoteNotFoundException extends ConflictException {

    public QuoteNotFoundException() {
        super(QUOTE_NOT_FOUND);
    }
}
