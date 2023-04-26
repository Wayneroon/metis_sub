package com.albamon.metis.controller.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class MetisRestControllerAdvice {

    public static final String ERROR_LOG_MESSAGE_KEY = "ERRLOG_MSG_KEY";        // 로그 DB 기록용 메시지 KEY\
    public static final String ERROR_LOG_CODE_KEY = "ERRLOG_CODE_KEY";        // 로그 DB 기록용 메시지 KEY\
    public static final String ERROR_SESSION_KEY = "ERRLOG_SESSION_KEY";        // 로그 DB 기록용 메시지 KEY\

    public static final String REQUEST_URI = "REQUEST_URI";
    public static final String ERROR_TYPE_CODE = "ERR_TYPE_CD";
    public static final String ERROR_PACKAGE_NAME = "ERROR_PACKAGE_NAME";

    /**
     * Validator 에 의해 처리된 메시지를 프론트에 전달한다.
     * MethodArgumentNotValidException 은 @Valid annotation 에 의해 발생되는 exception 이다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.debug("handleMethodArgumentNotValidException", ex);
        final ErrorResponse response = ErrorResponse.of(Error.INVALID_INPUT_VALUE, ex.getBindingResult());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**
     * Request Param validation exception
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.debug("handleConstraintViolationException", ex);
        final ErrorResponse response = ErrorResponse.of(Error.INVALID_INPUT_VALUE, ex.getConstraintViolations());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**
     * Dispatcher Servlet 에 Binding Exception 이 발생하면 해당 로그를 출력하는 메소드
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.debug("handleBindException", e);
        final ErrorResponse response = ErrorResponse.of(Error.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**
     * 지원하지 않은 HTTP Method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.debug("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(Error.METHOD_NOT_ALLOWED, e.getMessage());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**
     * 권한이 없는 리소스에 요청을 한 경우에 발생
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.debug("handleAccessDeniedException", e);
        final ErrorResponse response = ErrorResponse.of(Error.ACCESS_DENIED, e.getMessage());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**
     * Runtime Exception
     */
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(HttpServletRequest request, RuntimeException e) {
        final ErrorResponse response = getErrorResponse(e);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    private ErrorResponse getErrorResponse(Exception exception) {
        String errorCode = Error.SERVER_EXCEPTION.getErrorCode();
        Throwable rootCause = ExceptionUtils.getRootCause(exception);
        String clientMessage = getErrorMessage(exception);

        log.error("\t오류 : " + rootCause.getClass().getSimpleName() + " [" + StringUtils.trimWhitespace(rootCause.getLocalizedMessage()) + "]", exception);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, clientMessage);
    }

    /**
     * @Name  : getErrorMessage
     * @param e
     * @return String
     * 설명 : 상세 에러메세지 리턴
     */
    public String getErrorMessage(Exception e) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(bytes, true);
        e.printStackTrace(writer);
        return bytes.toString();
    }
}

