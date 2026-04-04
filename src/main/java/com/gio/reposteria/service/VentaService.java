package com.gio.reposteria.service;

import com.gio.reposteria.model.Insumo;
import com.gio.reposteria.model.ItemReceta;
import com.gio.reposteria.model.ItemVenta;
import com.gio.reposteria.model.Venta;
import com.gio.reposteria.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoService productoService;
    private final InsumoService insumoService;

    @Transactional
    public Venta registrarVenta(Venta venta) {
        venta.setFechaHora(LocalDateTime.now());
        double totalVenta = 0;

        for (ItemVenta itemVenta : venta.getItems()) {
            var producto = productoService.buscarPorId(itemVenta.getProducto().getId());
            itemVenta.setProducto(producto);
            itemVenta.setVenta(venta);

            double subtotal = producto.getPrecioVenta() * itemVenta.getCantidad();
            itemVenta.setSubtotal(subtotal);
            totalVenta += subtotal;

            if (producto.getReceta() != null) {
                for (ItemReceta itemReceta : producto.getReceta()) {
                    Insumo insumo = itemReceta.getInsumo();

                    double cantidadADescontar = itemReceta.getCantidadUsada() * itemVenta.getCantidad();

                    double nuevoStock = insumo.getCantidadActual() - cantidadADescontar;

                    if (nuevoStock < 0) throw new RuntimeException("Stock insuficiente de: " + insumo.getNombre());

                    insumo.setCantidadActual(Math.max(0, nuevoStock));
                    insumoService.guardar(insumo);
                }
            }
        }

        venta.setTotalVenta(totalVenta);
        return ventaRepository.save(venta);
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }
}