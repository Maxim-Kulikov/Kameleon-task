package com.example.kameleontask.util.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCode {
    MISSING_HEADER_PARAM("The following required header is missing: "),
    INVALID_REQUEST_PARAM_TYPE("The following request parameter has an invalid type: "),
    NOT_READABLE_REQUEST_BODY("The request body is unreadable or missing"),
    INVALID_REQUEST_PARAM("The following data does not pass validation: "),
    MISSING_REQUEST_PARAM("The following required data is missing: "),
    UNSUPPORTED_REQUEST("Request processing is not supported"),
    UNSUPPORTED_REQUEST_METHOD("The request method is not supported"),
    UNSUPPORTED_REQUEST_MEDIA_TYPE("The media type of the request is not supported"),
    BAD_REQUEST("Client request error"),
    INTERNAL_SERVER_ERROR("Internal server error"),

    USER_ALREADY_EXIST("User with email = %s or username = %s already exists"),
    USER_NOT_FOUND("User with id = %s not found"),
    QUOTE_NOT_FOUND("Quote not found"),
    USER_ALREADY_VOTED("User already voted");

    private final String message;
}
