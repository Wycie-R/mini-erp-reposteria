package com.gio.reposteria.controller;

import com.gio.reposteria.model.Venta;
import com.gio.reposteria.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService service;

    @GetMapping
    public List<Venta> listar() {
        return service.listarVentas();
    }

    @PostMapping
    public Venta registrar(@RequestBody Venta venta) {
        return service.registrarVenta(venta);
    }

    @GetMapping("/reporte")
    public List<Venta> obtenerReporte(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {

        return service.obtenerVentasPorFecha(inicio, fin);
    }
}