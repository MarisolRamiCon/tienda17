package com.example.tienda17.feign;

import com.example.tienda17.model.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "Pedido", url = "https://68d36382214be68f8c65a5c2.mockapi.io/api/v1")
public interface PedidosCliente {

    @GetMapping("/pedidos")
    List<Pedido> readAll();

    @GetMapping("/pedidos/{id}")
    Pedido readById(@PathVariable String id);

    @PostMapping("/pedidos")
    Pedido create(@RequestBody Pedido pedido);

    @PutMapping("/pedidos/{id}")
    Pedido update(@PathVariable String id, @RequestBody Pedido pedido);

    @DeleteMapping("/pedidos/{id}")
    void delete(@PathVariable String id);
}
