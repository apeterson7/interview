package org.finra.interview.exceptions;

import lombok.extern.log4j.Log4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@Log4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
//
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        log.warn("Returning HTTP 400 Bad Request", e);
        e.printStackTrace();
    }

    @ExceptionHandler({
            QuestionNotFoundException.class,
            FileNotFoundException.class
    })
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Book not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            DataIntegrityViolationException.class,
            QuestionAlreadyAssignedException.class,
            CandidateNotFoundException.class
    })
    public ResponseEntity<Object> handleBadRequest(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getLocalizedMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({
        InvalidFormatException.class,
        IOException.class
    })
    public  ResponseEntity<Object> handleInvalidDataError(
            Exception ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getLocalizedMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({
            NotOLE2FileException.class
    })
    public  ResponseEntity<Object> handleFileFormatError(
            Exception ex, WebRequest request) {

        return handleExceptionInternal(ex, "File format must be .xlsx or .xls",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}

