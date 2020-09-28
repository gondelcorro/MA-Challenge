package com.ma.pedidos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestProductos {

    @Test
    @Order(1)
    public void crearProducto1() {
        JSONObject request = new JSONObject();
        request.put("id", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        request.put("nombre", "Jamón y morrones");
        request.put("descripcionCorta", "Pizza de jamón y morrones");
        request.put("descripcionLarga", "Mozzarella, jamón, morrones y aceitunas verdes");
        request.put("precioUnitario", "550.00");

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("http://localhost:8080/productos").
                    then().statusCode(201).
                    log().all();
    }

    @Test
    @Order(2)
    public void crearProducto2() {
        JSONObject request = new JSONObject();
        request.put("id", "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1");
        request.put("nombre", "Palmitos");
        request.put("descripcionCorta", "Pizza de palmitos");
        request.put("descripcionLarga", "Mozzarella, palmitos y aceitunas verdes");
        request.put("precioUnitario", "600.00");

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("http://localhost:8080/productos").
                then().statusCode(201).
                log().all();
    }

    @Test
    @Order(3)
    public void modificarProducto() {
        JSONObject request = new JSONObject();
        request.put("id", "89efb206-2aa6-4e21-8a23-5765e3de1f31");
        request.put("nombre", "Jamón y morrones");
        request.put("descripcionCorta", "Pizza de jamón y morrones");
        request.put("descripcionLarga", "Mozzarella, jamón, morrones y aceitunas verdes");
        request.put("precioUnitario", "500.00");

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    put("http://localhost:8080/productos/89efb206-2aa6-4e21-8a23-5765e3de1f31").
                    then().statusCode(204).
                    log().all();
    }

    @Test
    @Order(4)
    void consultarProducto(){
        given()
                .get("http://localhost:8080/productos/89efb206-2aa6-4e21-8a23-5765e3de1f31")
                .then().statusCode(200)
                .log().body();
    }

    @Test
    @Order(5)
    void consultarProductoNoExistente(){
        given()
                .get("http://localhost:8080/productos/89efb206-2aa6-4e21-8a23-5765e3de1f30")
                .then().statusCode(404)
                .log().body();
    }

    //@Test
    @Order(6)
    void borrarProducto(){
        RestAssured.when()
                .delete("http://localhost:8080/productos/89efb206-2aa6-4e21-8a23-5765e3de1f31")
                .then().statusCode(204)
                .log().all();
    }
}
