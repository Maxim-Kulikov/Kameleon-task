package com.example.kameleontask.presentation.controller;

import com.example.kameleontask.presentation.dto.response.ErrorDto;
import com.example.kameleontask.util.exception.conflict.ConflictException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

import static com.example.kameleontask.util.exception.code.ExceptionCode.BAD_REQUEST;
import static com.example.kameleontask.util.exception.code.ExceptionCode.INTERNAL_SERVER_ERROR;
import static com.example.kameleontask.util.exception.code.ExceptionCode.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final String DEFAULT_ERROR_RESPONSE_LOG = "Http-response with error, responseDto={}";

    //    400 (BAD_REQUEST)
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex) {
        ErrorDto errorDto;
        if (ex instanceof MissingRequestHeaderException exception) {
            String errorMessage = MISSING_HEADER_PARAM.getMessage() + exception.getHeaderName();
            log.error("Http-request header is missing, errorMessage={}", errorMessage);
            errorDto = new ErrorDto(MISSING_HEADER_PARAM.name(), errorMessage);
        } else {
            log.error("Incorrect http-request, errorMessage={}", ex.getMessage());
            errorDto = new ErrorDto(BAD_REQUEST.name(), ex.getMessage());
        }
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.badRequest()
                .body(errorDto);
    }

    //    400 (BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatchException(TypeMismatchException ex) {
        ErrorDto errorDto;
        if (ex instanceof MethodArgumentTypeMismatchException exception) {
            String errorMessage = INVALID_REQUEST_PARAM_TYPE.getMessage() + exception.getName();
            log.error("Invalid http-request parameter type, errorMessage={}", errorMessage);
            errorDto = new ErrorDto(INVALID_REQUEST_PARAM_TYPE.name(), errorMessage);
        } else {
            log.error("Incorrect http-request, errorMessage={}", ex.getMessage());
            errorDto = new ErrorDto(BAD_REQUEST.name(), ex.getMessage());
        }
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.badRequest()
                .body(errorDto);
    }

    //    400 (BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = INVALID_REQUEST_PARAM.getMessage() + ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .distinct()
                .collect(Collectors.joining(", "));
        log.error("Http-request parameter fails validation, errorMessage={}", errorMessage);
        ErrorDto errorDto = new ErrorDto(INVALID_REQUEST_PARAM.name(), errorMessage);
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.badRequest()
                .body(errorDto);
    }

    //    400 (BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = INVALID_REQUEST_PARAM.getMessage() + ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .distinct()
                .collect(Collectors.joining(", "));
        log.error("Http-request parameter fails validation, errorMessage={}", errorMessage);
        ErrorDto errorDto = new ErrorDto(INVALID_REQUEST_PARAM.name(), errorMessage);
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.badRequest()
                .body(errorDto);
    }

    //    400 (BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("Http-request required parameter is missing");
        String errorMessage = MISSING_REQUEST_PARAM.getMessage() + ex.getParameterName();
        ErrorDto errorDto = new ErrorDto(MISSING_REQUEST_PARAM.name(), errorMessage);
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.badRequest()
                .body(errorDto);
    }

    //    400 (BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Http-request body is unreadable or missing, errorMessage={}", ex.getMessage());
        ErrorDto errorDto = new ErrorDto(NOT_READABLE_REQUEST_BODY.name(), NOT_READABLE_REQUEST_BODY.getMessage());
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.badRequest()
                .body(errorDto);
    }

    //    404 (NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.error("Http-request processing is not supported");
        ErrorDto errorDto = new ErrorDto(UNSUPPORTED_REQUEST.name(), UNSUPPORTED_REQUEST.getMessage());
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.status(NOT_FOUND)
                .body(errorDto);
    }

    //    405 (METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("Http-request method is not supported");
        ErrorDto errorDto = new ErrorDto(UNSUPPORTED_REQUEST_METHOD.name(), UNSUPPORTED_REQUEST_METHOD.getMessage());
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.status(METHOD_NOT_ALLOWED)
                .body(errorDto);
    }

    //    415 (UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.error("Http-request media type is not supported");
        ErrorDto errorDto = new ErrorDto(UNSUPPORTED_REQUEST_MEDIA_TYPE.name(), UNSUPPORTED_REQUEST_MEDIA_TYPE.getMessage());
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.status(UNSUPPORTED_MEDIA_TYPE)
                .body(errorDto);
    }

    // 409 (CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException ex) {
        log.error("Incorrect http-request, errorMessage={}", ex.getMessage());
        ErrorDto errorDto = new ErrorDto(ex.getCode(), ex.getMessage());
        log.error(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.status(CONFLICT)
                .body(errorDto);
    }

    //    500 (INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handledException(Exception ex) {
        log.error("Http-request processing error, errorMessage={}", ex.getMessage());
        log.debug("Error details: ", ex);
        ErrorDto errorDto = new ErrorDto(INTERNAL_SERVER_ERROR.name(), INTERNAL_SERVER_ERROR.getMessage());
        log.info(DEFAULT_ERROR_RESPONSE_LOG, errorDto);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorDto);
    }
}
