package com.company.sintra.exception;

import static com.company.sintra.exception.UniBootCampErrorCodes.AUTHORITY_NOT_FOUND;

public class AuthorityNotFoundException extends UniBootCampGenericException {

    public AuthorityNotFoundException(Object... argument) {
        super(AUTHORITY_NOT_FOUND.code, AUTHORITY_NOT_FOUND.code, 404, argument);
    }
}
