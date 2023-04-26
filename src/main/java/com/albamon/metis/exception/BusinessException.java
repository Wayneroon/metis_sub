package com.albamon.metis.exception;

import com.albamon.constants.ErrorCodes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 비지니스 로직 수행 중 발생하는 모든 예외처리를 위한 부모 Exception Class.
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String errorCode = ErrorCodes.BAD_REQUEST_INVALID_VALUE;

    public BusinessException() {
        super("Invalid Input Value");
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}

