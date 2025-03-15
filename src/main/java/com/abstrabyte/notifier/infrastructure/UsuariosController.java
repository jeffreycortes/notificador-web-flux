package com.abstrabyte.notifier.infrastructure;

import com.abstrabyte.notifier.application.UsuariosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/{userId}")
    public Mono<String> getUser(@PathVariable String userId) {
        return usuariosService.getUserById(userId);
    }

    @GetMapping("/{userId}/default")
    public Mono<String> getUserWithDefault(@PathVariable String userId) {
        return usuariosService.getUserByIdWithDefault(userId);
    }
}
