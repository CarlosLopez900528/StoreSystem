package com.example.store.exeption;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    public ExceptionResponse(LocalDateTime date, String message, String detail) {
        this.date = date;
        this.message = message;
        this.detail = detail;
    }

    private LocalDateTime date;
    private String message;
    private String detail;

}
