package com.ma.pedidos.util;

import com.ma.pedidos.model.EstadoPedidoEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ResponsePedidosDTO {

    private LocalDate fecha;
    private String direccion;
    private String email;
    private String telefono;
    private LocalTime horario;
    private List<ResponseProductosDTO> detalle;
    private float total;
    private boolean descuento;
    private EstadoPedidoEnum estado;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public List<ResponseProductosDTO> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<ResponseProductosDTO> detalle) {
        this.detalle = detalle;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }

    public EstadoPedidoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoEnum estado) {
        this.estado = estado;
    }
}
