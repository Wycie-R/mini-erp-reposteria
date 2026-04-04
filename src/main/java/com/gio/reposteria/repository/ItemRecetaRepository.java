package com.gio.reposteria.repository;

import com.gio.reposteria.model.ItemReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRecetaRepository extends JpaRepository<ItemReceta, Long> {
}