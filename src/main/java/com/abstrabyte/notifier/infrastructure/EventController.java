package com.abstrabyte.notifier.infrastructure;

import com.abstrabyte.notifier.domain.MessageEvent;
import com.abstrabyte.notifier.infrastructure.adapters.EventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventPublisher eventPublisher;

    public EventController(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    // Endpoint para suscribirse a un tópico
    @GetMapping(value = "/subscribe/{topic}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MessageEvent> subscribe(@PathVariable String topic) {
        return eventPublisher.subscribe()
                .filter(event -> event.topic.equalsIgnoreCase(topic));
    }

    // Endpoint para publicar un mensaje en un tópico
    @PostMapping("/publish")
    public void publish(@RequestBody MessageEvent event) {
        eventPublisher.publish(event);
    }

    @GetMapping("/version")
    public Mono<String> version() {
        return Mono.just("¡Hola desde WebFlux!");
    }
}


