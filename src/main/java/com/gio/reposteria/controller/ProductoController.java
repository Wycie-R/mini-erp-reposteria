package com.gio.reposteria.controller;

import com.gio.reposteria.model.Producto;
import com.gio.reposteria.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Esto es para que el Frontend local no tenga bloqueos
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return service.guardarProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}