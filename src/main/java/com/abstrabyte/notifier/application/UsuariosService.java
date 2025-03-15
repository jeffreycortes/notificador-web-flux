package com.abstrabyte.notifier.application;

import com.abstrabyte.notifier.domain.UserNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UsuariosService {
    public Mono<String> getUserById(String userId) {
        return findUserInDatabase(userId)
                .onErrorResume(error -> {
                    System.out.println("Error encontrado: " + error.getMessage());
                    return Mono.just("Usuario alternativo"); // Flujo alternativo en caso de error
                });
    }

    public Mono<String> getUserByIdWithDefault(String userId) {
        return findUserInDatabase(userId)
                .onErrorReturn("Usuario por defecto"); // Valor fijo en caso de error
    }

    private Mono<String> findUserInDatabase(String userId) {
        if ("123".equals(userId)) {
            return Mono.just("Usuario: Juan Pérez");
        }
        return Mono.error(new UserNotFoundException("Usuario con ID " + userId + " no encontrado"));
    }
}
