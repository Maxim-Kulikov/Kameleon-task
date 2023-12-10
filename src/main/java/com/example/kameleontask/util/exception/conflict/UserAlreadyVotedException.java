package com.example.kameleontask.util.exception.conflict;

import static com.example.kameleontask.util.exception.code.ExceptionCode.USER_ALREADY_VOTED;

public class UserAlreadyVotedException extends ConflictException {

    public UserAlreadyVotedException() {
        super(USER_ALREADY_VOTED);
    }
}
