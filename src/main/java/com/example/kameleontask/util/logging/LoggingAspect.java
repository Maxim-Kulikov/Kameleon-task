package com.example.kameleontask.util.logging;

import com.example.kameleontask.util.logging.annotation.LoggableDbQuery;
import com.example.kameleontask.util.logging.annotation.LoggableHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private static final String EMPTY_STRING = "";

    @Around("@annotation(com.example.kameleontask.util.logging.annotation.LoggableDbQuery)")
    public Object logDbSelect(ProceedingJoinPoint joinPoint) throws Throwable {
        LoggableDbQuery annotation = getAnnotation(joinPoint, LoggableDbQuery.class);
        log.debug("Request to database {}{}", annotation.description(), getParams(joinPoint));
        Object result = joinPoint.proceed();
        String resultStr = annotation.logResult() ? ", result=" + result : EMPTY_STRING;
        log.debug("Request to database {} was completed {}", annotation.description(), resultStr);
        return result;
    }

    @Around("@annotation(com.example.kameleontask.util.logging.annotation.LoggableHttpRequest)")
    public Object logHttpRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        LoggableHttpRequest annotation = getAnnotation(joinPoint, LoggableHttpRequest.class);
        log.info("Http-request {}{}", annotation.description(), getParams(joinPoint));
        Object response = joinPoint.proceed();
        String responseStr = annotation.logResponse() ? ", responseDto=" + response : EMPTY_STRING;
        log.info("Http-response {}{}", annotation.description(), responseStr);
        return response;
    }

    private <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> annotationClass) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(annotationClass);
    }

    private String getParams(JoinPoint joinPoint) {
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        return IntStream.range(0, paramNames.length).boxed()
                .map(i -> ((MethodSignature) joinPoint.getSignature()).getParameterNames()[i] + "=" + joinPoint.getArgs()[i])
                .collect(joining(", ", ", ", ""));
    }
}
