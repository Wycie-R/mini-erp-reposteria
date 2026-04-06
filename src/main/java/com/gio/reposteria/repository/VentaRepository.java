package com.gio.reposteria.repository;

import com.gio.reposteria.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

}