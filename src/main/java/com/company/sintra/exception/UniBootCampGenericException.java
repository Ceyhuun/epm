package com.company.sintra.exception;

import lombok.Getter;

@Getter
public  class UniBootCampGenericException extends RuntimeException {

    private final int status;
    private final String code;
    private final String message;
    private final transient Object[] argument;

    public UniBootCampGenericException(String code, String message, int status, Object... argument) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
        this.argument = argument == null ? new Object[0] : argument;
    }


//    public UniBootCampGenericException(String errorBody, HttpStatus httpStatus) {
//        super(errorBody);
//        this.status = httpStatus.value();
//        this.code = errorBody;
//        this.message = errorBody;
//        this.argument = null;
////    }
}
