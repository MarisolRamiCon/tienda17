package com.example.tienda17.controller;

import com.example.tienda17.feign.PedidosCliente;
import com.example.tienda17.model.Pedido;
import com.example.tienda17.service.impl.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidosController {
    private final PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @GetMapping
    public List<Pedido> getAll() {
        return pedidosService.readAll();
    }

    @GetMapping("/{id}")
    public Pedido getById(@PathVariable String id) {
        return pedidosService.readById(id);
    }

    @PostMapping
    public Pedido create(@RequestBody Pedido pedido) {
        return pedidosService.create(pedido);
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable String id, @RequestBody Pedido pedido) {
        return pedidosService.update(id, pedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        pedidosService.delete(id);
    }
}
