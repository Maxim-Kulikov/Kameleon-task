package com.example.kameleontask.util.exception.conflict;

import com.example.kameleontask.util.exception.code.ExceptionCode;
import lombok.Getter;

@Getter
public abstract class ConflictException extends RuntimeException {

    private final String code;

    public ConflictException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.code = exceptionCode.name();
    }

    public ConflictException(String message, String code) {
        super(message);
        this.code = code;
    }
}
