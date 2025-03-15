package com.abstrabyte.notifier.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {
    Mono<Producto> findTopByOrderByPrecioDesc();
}
