package com.abstrabyte.notifier.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("producto")
public class Producto {
    @Id
    private Integer id;
    @NotBlank(message = "EL nombre es obligatorio")
    private String nombre;
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private double precio;
}
