package com.abstrabyte.notifier.infrastructure;

import com.abstrabyte.notifier.domain.Producto;
import com.abstrabyte.notifier.domain.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersistenciaService {

    @Autowired
    private ProductoRepository productoRepository;

    public Mono<Producto> crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Mono<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Flux<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Mono<Void> eliminarProductoPorId(Long id) {
        return productoRepository.deleteById(id);
    }

    public Mono<Producto> actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Mono<Producto> obtenerProductoMasCaro(){
        return productoRepository.findTopByOrderByPrecioDesc();
    }
}
