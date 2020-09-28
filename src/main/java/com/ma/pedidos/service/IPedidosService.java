package com.ma.pedidos.service;

import com.ma.pedidos.model.PedidosCabecera;
import com.ma.pedidos.util.PedidosDTO;

import java.time.LocalDate;
import java.util.List;

public interface IPedidosService {

    PedidosCabecera registrar(PedidosDTO pedidosDTO);
    List<PedidosCabecera> listarPorFecha(LocalDate fecha);
}
