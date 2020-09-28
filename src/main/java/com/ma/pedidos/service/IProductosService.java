package com.ma.pedidos.service;

import com.ma.pedidos.model.Productos;

import java.util.List;

public interface IProductosService {

    void registrar(Productos producto);
    void elimiar(String idProducto);
    void modificar(Productos producto);
    Productos obtener(String idProducto);
    List<Productos> listar();
}
