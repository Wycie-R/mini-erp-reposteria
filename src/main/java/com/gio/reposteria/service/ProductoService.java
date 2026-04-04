package com.gio.reposteria.service;

import com.gio.reposteria.model.ItemReceta;
import com.gio.reposteria.model.Producto;
import com.gio.reposteria.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final InsumoService insumoService;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        if (producto.getReceta() != null) {
            for (ItemReceta item : producto.getReceta()) {
                item.setProducto(producto);
                item.setInsumo(insumoService.buscarPorId(item.getInsumo().getId()));
            }
        }

        calcularCosto(producto);

        return productoRepository.save(producto);
    }

    private void calcularCosto(Producto producto) {
        double costoTotal = 0;

        if (producto.getReceta() != null) {
            for (ItemReceta item : producto.getReceta()) {
                // Formula: Cantidad usada * Precio de compra del insumo
                // (Asumiendo que el precioCompra del insumo es por unidad de medida base)
                // Probablemente no sirva en muchos casos, por lo que tendré que agregar métodos más útiles y reales para calcular el costo
                double costoIngrediente = item.getCantidadUsada() * item.getInsumo().getPrecioCompra();
                costoTotal += costoIngrediente;
            }
        }

        producto.setCostoProduccion(costoTotal);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}