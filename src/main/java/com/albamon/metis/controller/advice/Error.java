package com.albamon.metis.controller.advice;

import com.albamon.constants.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum Error {
    /*HttpStatus.BAD_REQUEST*/
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST_INVALID_VALUE, "Invalid Input Value"),
    DATA_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST_NOT_FOUND, "Data Not Found Error"),
    FILE_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST_FILE_NOT_FOUND_EXCEPTION, "File Not Found Error"),
    INCORRECT_DATA_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST_INCORRECT_DATA_EXCEPTION, "Incorrect Data Error"),
    DUPLICATE_DATA_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST_DUPLICATE_DATA_EXCEPTION, "Duplicate Data Error"),
    LOGIN_INPUT_INVALID(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST_LOGIN_INPUT_INVALID, "Login input is invalid"),
    NOT_ALLOW_FILE_EXTENSION_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST_NOT_ALLOW_FILE_EXTENSION_EXCEPTION, "Not Allow File Extension Fail Error"),

    /*HttpStatus.METHOD_NOT_ALLOWED*/
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, ErrorCodes.METHOD_NOT_ALLOWED, "Method Not Allowed"),

    /*HttpStatus.FORBIDDEN*/
    ACCESS_DENIED(HttpStatus.FORBIDDEN, ErrorCodes.FORBIDDEN, "Access is Denied"),

    /*HttpStatus.INTERNAL_SERVER_ERROR*/
    SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    SQL_INTEGRITY(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.FORBIDDEN, "Internal Server Error"),
    DB_CONNECTION(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.FORBIDDEN, "Internal Server Error"),
    DB_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.FORBIDDEN, "Internal Server Error");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    Error(final HttpStatus httpStatus, final String errorCode, final String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
