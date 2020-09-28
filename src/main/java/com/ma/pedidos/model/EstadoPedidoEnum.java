package com.ma.pedidos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EstadoPedidoEnum {

    @JsonProperty("Pendiente")
    PENDIENTE,
    @JsonProperty("Entregado")
    ENTREGADO
}
