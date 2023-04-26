package com.albamon.metis.service.base;

import com.albamon.metis.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseService {

    public void throwIf(boolean condition, String errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }

    public void throwIf(boolean condition, String message) {
        throwIf(condition, new BusinessException(message));
    }

    public void throwIf(boolean condition, RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }
}
