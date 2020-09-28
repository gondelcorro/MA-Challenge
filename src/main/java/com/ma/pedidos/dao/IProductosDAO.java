package com.ma.pedidos.dao;

import com.ma.pedidos.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductosDAO extends JpaRepository<Productos, Integer> {

    @Query(value = "SELECT * FROM productos p WHERE p.id=:id LIMIT 1", nativeQuery = true)
    Productos obtenerProducto(@Param("id") String id);
}
