package com.abstrabyte.notifier.infrastructure.adapters;

import com.abstrabyte.notifier.domain.MessageEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Flux;

@Component
public class EventPublisher {
    private final Sinks.Many<MessageEvent> sink;

    public EventPublisher() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    public void publish(MessageEvent event) {
        sink.tryEmitNext(event);
    }

    public Flux<MessageEvent> subscribe() {
        return sink.asFlux();
    }
}
