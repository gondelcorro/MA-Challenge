package com.ma.pedidos;

import com.ma.pedidos.util.PedidosDTO;
import com.ma.pedidos.util.ProductosDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPedidos {

    @Test
    @Order(1)
    void crearPedido(){
        PedidosDTO pedido = new PedidosDTO();
        pedido.setDireccion("Dorton Road 80");
        pedido.setEmail("tsayb@opera.com");
        pedido.setTelefono("(0351) 48158101");
        pedido.setHorario(LocalTime.of(21,00));
        List<ProductosDTO> detalle = new ArrayList<>();
        ProductosDTO producto1 = new ProductosDTO();
        producto1.setProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31");
        producto1.setCantidad(1);
        ProductosDTO producto2 = new ProductosDTO();
        producto2.setProducto("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        producto2.setCantidad(1);
        detalle.add(producto1);
        detalle.add(producto2);
        pedido.setDetalle(detalle);

        RestAssured.given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(pedido)
                .when()
                    .post("http://localhost:8080/pedidos")
                    .then().statusCode(201)
                    .log().body();
    }

    @Test
    @Order(2)
    void crearPedidoConDescuento(){
        PedidosDTO pedido = new PedidosDTO();
        pedido.setDireccion("Dorton Road 80");
        pedido.setEmail("tsayb@opera.com");
        pedido.setTelefono("(0351) 48158101");
        pedido.setHorario(LocalTime.of(21,00));
        List<ProductosDTO> detalle = new ArrayList<>();
        ProductosDTO producto1 = new ProductosDTO();
        producto1.setProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31");
        producto1.setCantidad(2);
        ProductosDTO producto2 = new ProductosDTO();
        producto2.setProducto("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        producto2.setCantidad(2);
        detalle.add(producto1);
        detalle.add(producto2);
        pedido.setDetalle(detalle);

        RestAssured.given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(pedido)
                .when()
                .post("http://localhost:8080/pedidos")
                .then().statusCode(201)
                .log().body();
    }

    @Test
    @Order(3)
    void crearPedidoConErrores(){
        PedidosDTO pedido = new PedidosDTO();
        pedido.setEmail("tsayb@opera.com");
        pedido.setTelefono("(0351) 48158101");
        pedido.setHorario(LocalTime.of(21,00));
        List<ProductosDTO> detalle = new ArrayList<>();
        ProductosDTO producto1 = new ProductosDTO();
        producto1.setProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31");
        producto1.setCantidad(2);
        ProductosDTO producto2 = new ProductosDTO();
        producto2.setProducto("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        producto2.setCantidad(2);
        detalle.add(producto1);
        detalle.add(producto2);
        pedido.setDetalle(detalle);

        RestAssured.given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(pedido)
                .when()
                .post("http://localhost:8080/pedidos")
                .then().statusCode(400)
                .log().body();
    }

    @Test
    @Order(4)
    void listaPedidosPorFecha(){
        RestAssured.given()
                .queryParam("fecha", "2020-09-28")
                .when()
                    .get("http://localhost:8080/pedidos")
                    .then().statusCode(200)
                    .log().all();

    }
}
