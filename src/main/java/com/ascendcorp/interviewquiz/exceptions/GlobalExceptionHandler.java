package com.ascendcorp.interviewquiz.exceptions;

import com.ascendcorp.interviewquiz.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDateTimeParseException(DateTimeParseException ex) {
        // กรณี date ผิด format
        return new ErrorResponse(
                "ERR_INVALID_DATE_FORMAT",
                "Invalid date format: " + ex.getParsedString(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        // กรณีเจอ argument ไม่ถูกต้อง เช่น startDate > endDate, year ติดลบ
        return new ErrorResponse(
                "ERR_ILLEGAL_ARGUMENT",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(ExternalApiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleExternalApiException(ExternalApiException ex) {
        return new ErrorResponse(
                "ERR_EXTERNAL_API",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherExceptions(Exception ex) {
        return new ErrorResponse(
                "ERR_INTERNAL_SERVER_ERROR",
                "Internal Server Error: " + ex.getMessage(),
                LocalDateTime.now()
        );
    }
}

