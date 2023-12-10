package com.example.kameleontask.util.exception.conflict;

import static com.example.kameleontask.util.exception.code.ExceptionCode.USER_NOT_FOUND;

public class UserNotFoundException extends ConflictException {

    public UserNotFoundException(Long id) {
        super(String.format(USER_NOT_FOUND.getMessage(), id.toString()), USER_NOT_FOUND.name());
    }
}
