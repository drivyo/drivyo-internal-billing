package Drivyo.sharedData.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
@AllArgsConstructor
public enum ErrorCatalog {
    ELEMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "DTA-001", "The element identified with the provided id could " +
            "not be found."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH-002", "Invalid username or password."),

    INVALID_ROLE(HttpStatus.UNAUTHORIZED, "PRM-001", "The user does not have enough permissions to perform the action."),

    INCOMPLETE_COMPANY_DATA_NO_NAME(HttpStatus.BAD_REQUEST,"DTA-002", "Incomplete data: name should not be empty"),
    INCOMPLETE_COMPANY_DATA_NO_CIF(HttpStatus.BAD_REQUEST,"DTA-003", "Incomplete data: cif should not be empty"),
    DUPLICATED_COMPANY_CIF(HttpStatus.CONFLICT,"DTA-004", "Duplicated data: the given company cif already exists in the database"),
    DUPLICATED_COMPANY_NAME(HttpStatus.CONFLICT,"DTA-005", "Duplicated data: the given company name already exists in the database"),
    UPDATE_WITHOUT_ID(HttpStatus.BAD_REQUEST, "DTA-006", "PUT operations require an id to identify the resource to be replaced.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public HttpException getException() {
        log.error(String.format("HTTP STATUS: %s CODE: %s MESSAGE: %s", this.httpStatus, this.code, this.message));
        return new HttpException(this.httpStatus, this.code, this.message);
    }

    }
