package com.company.sintra.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UniBootCampErrorCodes {
    USER_NOT_FOUND("USER-NOT-FOUND"),
    EXIST_USER_NAME("USER-ALREADY-EXIST"),
    LENGTH_NOT_VALID("LENGTH-NOT-VALID"),
    AUTHORITY_NOT_FOUND("AUTHORITY-NOT-FOUND"),
    INVALID_PASSWORD("PASSWORD-INVALID");
    public final String code;
}
