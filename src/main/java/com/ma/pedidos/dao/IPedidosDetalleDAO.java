package com.ma.pedidos.dao;

import com.ma.pedidos.model.PedidosDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidosDetalleDAO extends JpaRepository<PedidosDetalle, Integer> {
}
