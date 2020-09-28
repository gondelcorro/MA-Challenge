package com.ma.pedidos.service.impl;

import com.ma.pedidos.dao.IProductosDAO;
import com.ma.pedidos.model.Productos;
import com.ma.pedidos.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImpl implements IProductosService {

    @Autowired
    private IProductosDAO daoProductos;

    @Override
    public void registrar(Productos producto) {
        daoProductos.save(producto);
    }

    @Override
    public void elimiar(String idProducto) {
        try {
            Productos producto = daoProductos.obtenerProducto(idProducto);
            if(producto != null){
                daoProductos.delete(producto);
            }
        }catch (Exception e){
        }
    }

    @Override
    public void modificar(Productos producto) {
        Productos save = daoProductos.save(producto);
    }

    @Override
    public Productos obtener(String idProducto) {
        Productos producto = null;
        try {
            producto = daoProductos.obtenerProducto(idProducto);
        }catch (Exception e){
        }
        return producto;
    }

    @Override
    public List<Productos> listar() {
        return daoProductos.findAll();
    }
}
