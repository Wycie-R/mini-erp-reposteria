package com.gio.reposteria.controller;

import com.gio.reposteria.model.Insumo;
import com.gio.reposteria.repository.InsumoRepository;
import com.gio.reposteria.service.InsumoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (ajustar luego por seguridad)
public class InsumoController {

    private final InsumoService service;
    private final InsumoRepository repository;

    @GetMapping
    public List<Insumo> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Insumo crear(@Valid @RequestBody Insumo insumo) {
        return service.guardar(insumo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/{id}")
    public Insumo obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/alertas")
    public List<Insumo> obtenerAlertasStock() {
        return repository.encontrarInsumosEnAlerta();
    }
}