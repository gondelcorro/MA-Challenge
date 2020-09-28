package com.ma.pedidos.dao;

import com.ma.pedidos.model.PedidosCabecera;
import com.ma.pedidos.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPedidosCabeceraDAO extends JpaRepository<PedidosCabecera, Integer> {

    @Query(value = "SELECT * FROM pedidos_cabecera p WHERE p.fecha_alta=:fecha", nativeQuery = true)
    List<PedidosCabecera> listarPorFecha(@Param("fecha") LocalDate fecha);
}
