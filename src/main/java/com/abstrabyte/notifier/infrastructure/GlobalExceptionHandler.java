package com.abstrabyte.notifier.infrastructure;

import com.abstrabyte.notifier.domain.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        return Mono.just(Map.of(
                "error", "Usuario no encontrado",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<Map<String, String>> handleGenericException(Exception ex) {
        return Mono.just(Map.of(
                "error", "Error interno del servidor",
                "message", ex.getMessage()
        ));
    }
}
