package com.project.config.exceptions;

import com.project.utils.date.DateUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Data
public class ApiError {

    private long localDateTime;
    private int status;
    private String error;
    private String exception;
    private String message;
    private String technicalMessage;
    private Object resultErrors;
    private String path;

    private ApiError() {
        localDateTime = DateUtils.getCurrentEpoch();
        resultErrors = "";
    }

    ApiError(Throwable ex, HttpStatus status, WebRequest request) {
        this();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.exception = ex.getClass().getTypeName();
        this.message = ex.getMessage();
        this.path = ((ServletWebRequest) request).getRequest().getServletPath();
    }

    ApiError(Throwable ex, HttpStatus status, String path) {
        this();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.exception = ex.getClass().getTypeName();
        this.message = ex.getMessage();
        this.path = path;
    }

    public ApiError(Throwable ex, String message, HttpStatus status, WebRequest request) {
        this();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.exception = ex.getClass().getTypeName();
        this.message = ex.getMessage();
        this.technicalMessage = ex.getMessage();
        this.path = ((ServletWebRequest) request).getRequest().getServletPath();
    }
}