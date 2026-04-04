package com.gio.reposteria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data // Genera getters, setters, toString, equals y hashcode automáticamente
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private double cantidadActual;

    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private double stockMinimo;

    @Min(value = 0, message = "El precio debe ser mayor a 0")
    private double precioCompra;

    private String unidadMedida; // Ej: "Kg", "Unidades", "Litros"
}