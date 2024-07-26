package com.exavalu.agentportal.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({PolicyNotFoundException.class,FileUploadErrorException.class,UserException.class,FNOLErrorException.class,ClaimNotFoundException.class})
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<CustomErrorResponse> customHandle4xxException(HttpClientErrorException ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getResponseBodyAsString());
        errors.setStatus(ex.getRawStatusCode());
        return new ResponseEntity<>(errors, ex.getStatusCode());

    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<CustomErrorResponse> customHandle5xxException(HttpServerErrorException ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnknownHttpStatusCodeException.class)
    public ResponseEntity<CustomErrorResponse> customHandleUnknownHTTPException(UnknownHttpStatusCodeException ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getResponseBodyAsString());
        errors.setStatus(ex.getRawStatusCode());
        return new ResponseEntity<>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}