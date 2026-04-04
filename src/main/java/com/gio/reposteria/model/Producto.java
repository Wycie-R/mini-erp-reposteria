package com.gio.reposteria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private double costoProduccion;

    private double precioVenta;

    // Si borramos un producto, se borra su receta.
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemReceta> receta = new ArrayList<>();

    public void agregarIngrediente(ItemReceta item) {
        receta.add(item);
        item.setProducto(this);
    }
}