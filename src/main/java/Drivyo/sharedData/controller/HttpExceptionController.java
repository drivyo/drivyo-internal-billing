package Drivyo.sharedData.controller;

import Drivyo.sharedData.dto.HttpExceptionResponse;
import Drivyo.sharedData.exceptions.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class HttpExceptionController {

    public static final String EXCEPTION_DETAIL = "Exception detail";

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<HttpExceptionResponse> handleHttpException(final HttpException httpException) {
        final HttpExceptionResponse exceptionResponse = HttpExceptionResponse.builder()
                .code(httpException.getCode())
                .genericMessage(httpException.getGenericMessage())
                .clarifiedMessage(httpException.getClarifiedMessage())
                .build();
        log.error(EXCEPTION_DETAIL, httpException);
        return new ResponseEntity<>(exceptionResponse, httpException.getHttpStatus());
    }
}
