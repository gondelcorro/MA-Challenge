package com.ma.pedidos.controller;

import com.ma.pedidos.model.PedidosCabecera;
import com.ma.pedidos.model.Productos;
import com.ma.pedidos.service.IPedidosService;
import com.ma.pedidos.service.IProductosService;
import com.ma.pedidos.util.PedidosDTO;
import com.ma.pedidos.util.ProductosDTO;
import com.ma.pedidos.util.ResponsePedidosDTO;
import com.ma.pedidos.util.ResponseProductosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private IPedidosService servicePedidos;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePedidosDTO> registrar(@RequestBody PedidosDTO pedidosDTO){
        ResponsePedidosDTO pedidoResponse = new ResponsePedidosDTO();
        try{
            if(pedidosDTO.getDireccion() != null ){
                PedidosCabecera pedidosCabecera = servicePedidos.registrar(pedidosDTO);
                pedidoResponse = crearResponse(pedidoResponse, pedidosCabecera);
                return new ResponseEntity<ResponsePedidosDTO>(pedidoResponse, HttpStatus.CREATED);
            }else{
                Map<String, String> response = new HashMap<>();
                response.put("error", "La direccion no puede ser nula");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<ResponsePedidosDTO>(pedidoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponsePedidosDTO>> listarPorFecha(@RequestParam(name = "fecha")  String fecha){
        List<PedidosCabecera> pedidosPorFecha = new ArrayList<>();
        List<ResponsePedidosDTO> pedidosResponse = new ArrayList<>();
        LocalDate fechaPedido = LocalDate.parse(fecha);
        try{
            pedidosPorFecha = servicePedidos.listarPorFecha(fechaPedido);
            pedidosPorFecha.forEach(pedido -> {
                ResponsePedidosDTO responsePedidosDTO = new ResponsePedidosDTO();
                responsePedidosDTO = crearResponse(responsePedidosDTO, pedido);
                pedidosResponse.add(responsePedidosDTO);
            });
            return new ResponseEntity<List<ResponsePedidosDTO>>(pedidosResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<ResponsePedidosDTO>>(pedidosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponsePedidosDTO crearResponse(ResponsePedidosDTO pedidoResponse, PedidosCabecera pedidosCabecera){
        pedidoResponse.setFecha(pedidosCabecera.getFechaAlta());
        pedidoResponse.setDireccion(pedidosCabecera.getDireccion());
        pedidoResponse.setEmail(pedidosCabecera.getEmail());
        pedidoResponse.setTelefono(pedidosCabecera.getTelefono());
        pedidoResponse.setHorario(pedidosCabecera.getHorario());
        List<ResponseProductosDTO> listResponseProd = new ArrayList<>();
        pedidosCabecera.getDetalle().forEach(det -> {
            ResponseProductosDTO productosDTO = new ResponseProductosDTO();
            productosDTO.setProducto(det.getProducto().getId());
            productosDTO.setNombre(det.getProducto().getNombre());
            productosDTO.setCantidad(det.getCantidad());
            productosDTO.setImporte(det.getImporte());
            listResponseProd.add(productosDTO);
        });
        pedidoResponse.setDetalle(listResponseProd);
        pedidoResponse.setTotal(pedidosCabecera.getMontoTotal());
        pedidoResponse.setDescuento(pedidosCabecera.isAplicoDescuento());
        pedidoResponse.setEstado(pedidosCabecera.getEstado());

        return pedidoResponse;
    }
}
