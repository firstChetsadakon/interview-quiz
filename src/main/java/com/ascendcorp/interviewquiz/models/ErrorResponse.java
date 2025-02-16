package com.ascendcorp.interviewquiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String message;
    private LocalDateTime timestamp;

}

