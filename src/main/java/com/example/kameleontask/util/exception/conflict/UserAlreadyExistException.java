package com.example.kameleontask.util.exception.conflict;

import static com.example.kameleontask.util.exception.code.ExceptionCode.USER_ALREADY_EXIST;

public class UserAlreadyExistException extends ConflictException {

    public UserAlreadyExistException(String email, String username) {
        super(String.format(USER_ALREADY_EXIST.getMessage(), email, username), USER_ALREADY_EXIST.name());
    }
}
