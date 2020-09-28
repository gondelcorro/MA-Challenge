package com.ma.pedidos.util;

import java.time.LocalTime;
import java.util.List;

public class PedidosDTO {

    private String direccion;
    private String email;
    private String telefono;
    private LocalTime horario;
    private List<ProductosDTO> detalle;

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

    public List<ProductosDTO> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<ProductosDTO> detalle) {
        this.detalle = detalle;
    }
}
