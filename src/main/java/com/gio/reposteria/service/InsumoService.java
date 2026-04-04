package com.gio.reposteria.service;

import com.gio.reposteria.model.Insumo;
import com.gio.reposteria.repository.InsumoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Lombok genera el constructor para la inyección de dependencias
public class InsumoService {

    private final InsumoRepository repository;

    public List<Insumo> listarTodos() {
        return repository.findAll();
    }

    public Insumo guardar(Insumo insumo) {
        // Aquí podrías agregar lógica extra, ej: redondear precios
        return repository.save(insumo);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Insumo buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Insumo no encontrado con ID: " + id));
    }
}