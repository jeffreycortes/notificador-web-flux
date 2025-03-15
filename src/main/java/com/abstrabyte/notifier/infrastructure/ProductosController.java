package com.abstrabyte.notifier.infrastructure;

import com.abstrabyte.notifier.domain.Producto;
import com.abstrabyte.notifier.domain.ProductoDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/productos")
@Validated
public class ProductosController {

    private static final Logger logger = LoggerFactory.getLogger(ProductosController.class);

    @Autowired
    private PersistenciaService persistenciaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Producto> obtenerTodosLosProductos() {
        return persistenciaService.obtenerTodosLosProductos();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Producto> obtenerProductoPorId(@PathVariable("id") Long id) {
        return persistenciaService.obtenerProductoPorId(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        return persistenciaService.crearProducto(producto);
    }

    @PostMapping(value="/v2")
    public Mono<ProductoDto> crearProductoV2(@RequestBody ProductoDto producto) {
        return Mono.just(producto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Producto> actualizarProducto(@PathVariable("id") Integer id, @RequestBody Producto producto) {
        //producto.setId(id);
        return persistenciaService.actualizarProducto(producto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminarProducto(@PathVariable("id") Long id) {
        return persistenciaService.eliminarProductoPorId(id);
    }

    @GetMapping(value = "/mas-caro", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Producto> obtenerProductoMasCaro() {
        return persistenciaService.obtenerProductoMasCaro();
    }
}
