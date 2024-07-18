package com.matnrocha.book_network.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum BussinessErrorCodes {

    //todo list all errors, http status and descriptions

    ;
    @Getter
    private int code;
    @Getter
    private String description;
    @Getter
    private HttpStatus httpStatus;

    BussinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
