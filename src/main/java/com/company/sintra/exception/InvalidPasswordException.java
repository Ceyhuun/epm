package com.company.sintra.exception;

import static com.company.sintra.exception.UniBootCampErrorCodes.INVALID_PASSWORD;

public class InvalidPasswordException extends UniBootCampGenericException {

    public InvalidPasswordException(Object... argument) {
        super(INVALID_PASSWORD.code, INVALID_PASSWORD.code, 403, argument);
    }

}
