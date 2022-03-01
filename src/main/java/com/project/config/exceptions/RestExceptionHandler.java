package com.project.config.exceptions;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleThrowable(Throwable ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        //registerErrorLog(ex, request);
        return handleUnexpectedException(ex, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        //registerErrorLog(ex, request);
        return handleUnexpectedException(ex, headers, HttpStatus.NOT_FOUND, request);
    }

    protected ResponseEntity<Object> handleUnexpectedException(Throwable ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(log.isErrorEnabled())
            log.error(ex.getMessage(), ex);

        request.setAttribute("technicalMessage", ex.getMessage(),0);

        return this.handleExceptionInternal(ex, new ApiError(ex,"Unexpected server error. Call technical support.",status,request), headers, status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Throwable ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }

        return new ResponseEntity(body, headers, status);
    }
}
