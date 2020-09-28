package com.ma.pedidos.service.impl;

import com.ma.pedidos.dao.IPedidosCabeceraDAO;
import com.ma.pedidos.dao.IProductosDAO;
import com.ma.pedidos.model.EstadoPedidoEnum;
import com.ma.pedidos.model.PedidosCabecera;
import com.ma.pedidos.model.PedidosDetalle;
import com.ma.pedidos.service.IPedidosService;
import com.ma.pedidos.util.PedidosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidosServiceImpl implements IPedidosService {

    @Autowired
    private IPedidosCabeceraDAO daoPedidosCabecera;

    @Autowired
    private IProductosDAO daoProductos;

    private final int NUM_ART_DSCTO = 3;
    private final int PORCENTAJE_DSCTO = 30;

    @Override
    public PedidosCabecera registrar(PedidosDTO pedidosDTO) {
        PedidosCabecera pedido = new PedidosCabecera();
        List<PedidosDetalle> listaPedidos = new ArrayList<>();
        try {
            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            pedido.setFechaAlta(LocalDate.parse(fecha));
            pedido.setDireccion(pedidosDTO.getDireccion());
            pedido.setEmail(pedidosDTO.getEmail());
            pedido.setTelefono(pedidosDTO.getTelefono());
            String horario = pedidosDTO.getHorario().format(DateTimeFormatter.ofPattern("HH:mm"));
            pedido.setHorario(LocalTime.parse(horario));
            pedidosDTO.getDetalle().forEach( det ->{
                PedidosDetalle detalle = new PedidosDetalle();
                detalle.setPedidoCabecera(pedido);
                detalle.setProducto(daoProductos.obtenerProducto(det.getProducto()));
                detalle.setCantidad(det.getCantidad());
                detalle.setImporte(det.getCantidad() * daoProductos.obtenerProducto(det.getProducto()).getPrecioUnitario());
                listaPedidos.add(detalle);
            });
            pedido.setDetalle(listaPedidos);
            float total = 0;
            int cantProductos = 0;
            for(PedidosDetalle p : listaPedidos){
                total = total + p.getImporte();
                cantProductos = cantProductos + p.getCantidad();
            }
            if(cantProductos > NUM_ART_DSCTO ){
                float dscto = (total * PORCENTAJE_DSCTO) / 100;
                pedido.setMontoTotal(total - dscto);
                pedido.setAplicoDescuento(true);
            }else{
                pedido.setMontoTotal(total);
                pedido.setAplicoDescuento(false);
            }
            pedido.setEstado(EstadoPedidoEnum.PENDIENTE);
            daoPedidosCabecera.save(pedido);
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
        return pedido;
    }

    @Override
    public List<PedidosCabecera> listarPorFecha(LocalDate fecha) {
        return daoPedidosCabecera.listarPorFecha(fecha);
    }
}
