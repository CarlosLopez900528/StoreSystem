package com.example.store.exeption;

import java.time.LocalDateTime;

public class ExceptionResponse {

    public ExceptionResponse() {
    }

    public ExceptionResponse(LocalDateTime date, String message, String detail) {
        this.date = date;
        this.message = message;
        this.detail = detail;
    }

    private LocalDateTime date;
    private String message;
    private String detail;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
