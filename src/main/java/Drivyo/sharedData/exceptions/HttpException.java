package Drivyo.sharedData.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {
    private static final String DEFAULT_CLARIFIED_MSG = "There is no particular clarification for this error";
    private final HttpStatus httpStatus;
    private final String code;
    private final String genericMessage;
    private final String clarifiedMessage;

    public HttpException(HttpStatus httpStatus, String code, String genericMessage) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.genericMessage = genericMessage;
        this.clarifiedMessage = DEFAULT_CLARIFIED_MSG;
    }

    public HttpException(HttpStatus httpStatus, String code, String genericMessage, String clarifiedMessage) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.genericMessage = genericMessage;
        this.clarifiedMessage = clarifiedMessage;
    }
}