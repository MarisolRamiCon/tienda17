package com.example.tienda17.service.impl;

import com.example.tienda17.feign.PedidosCliente;
import com.example.tienda17.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosService{

    private final PedidosCliente pedidosCliente;

    public PedidosService(PedidosCliente pedidosCliente) {
        this.pedidosCliente = pedidosCliente;
    }

    public List<Pedido> readAll() {
        return pedidosCliente.readAll().stream().filter(p -> p.getActivo()).toList();
    }

    public Pedido readById(String id) {
        return pedidosCliente.readById(id);
    }

    public Pedido create(Pedido pedido) {
        return pedidosCliente.create(pedido);
    }

    public Pedido update(String id, Pedido pedido) {
        return pedidosCliente.update(id, pedido);
    }

    public Pedido delete(String id) {
        Pedido pedido = pedidosCliente.readById(id);
        pedido.setActivo(false);
        return pedidosCliente.update(id, pedido);
    }

}
