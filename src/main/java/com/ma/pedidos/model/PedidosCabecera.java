package com.ma.pedidos.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="pedidos_cabecera")
public class PedidosCabecera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "direccion", nullable = false, length = 250)
    private String direccion;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefono", nullable = false, length = 100)
    private String telefono;

    @JsonSerialize(using= ToStringSerializer.class)
    private LocalTime horario;

    @JsonSerialize(using= ToStringSerializer.class)
    private LocalDate fechaAlta;

    @Column(name = "monto_total", nullable = false, length = 20)
    private float montoTotal;

    @Column(name = "aplico_descuento", nullable = false)
    private boolean aplicoDescuento;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPedidoEnum estado;

    @OneToMany(mappedBy="pedidoCabecera" , cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} , fetch=FetchType.LAZY, orphanRemoval=true)
    private List<PedidosDetalle> detalle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public boolean isAplicoDescuento() {
        return aplicoDescuento;
    }

    public void setAplicoDescuento(boolean aplicoDescuento) {
        this.aplicoDescuento = aplicoDescuento;
    }

    public EstadoPedidoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoEnum estado) {
        this.estado = estado;
    }

    public List<PedidosDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<PedidosDetalle> detalle) {
        this.detalle = detalle;
    }
}
