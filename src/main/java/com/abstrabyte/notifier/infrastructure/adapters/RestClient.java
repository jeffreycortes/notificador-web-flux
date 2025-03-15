package com.abstrabyte.notifier.infrastructure.adapters;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RestClient {
    private final WebClient webClient;

    public RestClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.ejemplo.com") // URL base para las peticiones
                .build();
    }

    public <T> Object get(Class<T> type) {
        return webClient.get()
                .uri("http://cols-as-digprod-portalempresas-front-integration.azurewebsites.net/version")
                .header("Authorization", "Bearer token")
                .retrieve()
                .bodyToMono(type);
    }

    public <T>Mono post(Object datos, Class<T> type) {
        return webClient.post()
                .uri("https://app-afi-afiliados-api-test.azurewebsites.net/v1.0/app-affiliates/api/affiliates")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(datos) // Envía el objeto como JSON
                .retrieve()
                .bodyToMono(type);
    }
}
