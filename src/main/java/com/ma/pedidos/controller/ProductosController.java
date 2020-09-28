package com.ma.pedidos.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ma.pedidos.model.Productos;
import com.ma.pedidos.service.IProductosService;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private IProductosService serviceProductos;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registrar(@RequestBody Productos producto){
        try{
            serviceProductos.registrar(producto);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity modificar(@RequestBody  Map<String, Object> dataProducto, @PathVariable("id") String idProducto){
        try{
            Productos producto =  serviceProductos.obtener(idProducto);
            if(producto != null){
                Productos nuevoProducto = new Productos();
                nuevoProducto.setId(producto.getId());
                nuevoProducto.setNombre(dataProducto.get("nombre").toString());
                nuevoProducto.setDescripcionCorta(dataProducto.get("descripcionCorta").toString());
                nuevoProducto.setDescripcionLarga(dataProducto.get("descripcionLarga").toString());
                nuevoProducto.setPrecioUnitario(Float.parseFloat(dataProducto.get("precioUnitario").toString()));
                serviceProductos.modificar(nuevoProducto);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }else{
                Map<String, String> response = new HashMap<>();
                response.put("error", "Producto no encontrado");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Productos> consultar(@PathVariable("id") String idProducto){
        Productos producto = new Productos();
        try{
            producto =  serviceProductos.obtener(idProducto);
            if(producto != null){
                return new ResponseEntity<Productos>(producto, HttpStatus.OK);
            }else{
                Map<String, String> response = new HashMap<>();
                response.put("error", "Producto no encontrado");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("error", "Producto no encontrado");
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity borrar(@PathVariable("id") String idProducto){
        try{
            serviceProductos.elimiar(idProducto);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
