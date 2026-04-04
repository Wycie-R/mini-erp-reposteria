package com.gio.reposteria.repository;

import com.gio.reposteria.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    // Acá puedo usar métodos abreviados de spring

    @Query("SELECT i FROM Insumo i WHERE i.cantidadActual <= i.stockMinimo")
    List<Insumo> encontrarInsumosEnAlerta();
}