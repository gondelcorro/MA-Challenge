package com.ma.pedidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name="pedidos_detalle")
public class PedidosDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_pedido", nullable = false)
    private PedidosCabecera pedidoCabecera;

    @ManyToOne
    @JoinColumn(name="id_producto", nullable = false)
    private Productos producto;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "importe", nullable = false)
    private float importe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PedidosCabecera getPedidoCabecera() {
        return pedidoCabecera;
    }

    public void setPedidoCabecera(PedidosCabecera pedidoCabecera) {
        this.pedidoCabecera = pedidoCabecera;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
}
