package com.company.sintra.exception;

import com.company.sintra.service.impl.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GenericExceptionHandler {

    private final TranslationService translationService;

    @ExceptionHandler(UniBootCampGenericException.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(UniBootCampGenericException ex, WebRequest request) {
        ex.getStackTrace();
        var path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        String lang = request.getHeader(ACCEPT_LANGUAGE);
        return createErrorResponse(ex, path, request);
    }

    private ResponseEntity<ErrorResponseDTO> createErrorResponse(UniBootCampGenericException ex,
                                                                 String path, WebRequest request) {
        ErrorResponseDTO build = ErrorResponseDTO.builder()
                .status(ex.getStatus())
                .code(ex.getCode())
                .path(path)
                .timestamp(OffsetDateTime.now())
                .message(translationService.findByKey(ex.getMessage(), "en"))
                .detail(translationService.findByKey(ex.getMessage().concat("_DETAIL"), "en"))
                .build();
        return ResponseEntity
                .status(ex.getStatus())
                .body(build);
    }

}
