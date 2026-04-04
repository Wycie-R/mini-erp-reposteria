package com.gio.reposteria.repository;

import com.gio.reposteria.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    // Acá puedo usar métodos abreviados de spring
}