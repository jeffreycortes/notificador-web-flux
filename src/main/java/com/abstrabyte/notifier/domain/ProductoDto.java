package com.abstrabyte.notifier.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;

@Data
public class ProductoDto {
    private Integer id;

    private String nombre;

    private String descripcion;

    private double precio;
}
