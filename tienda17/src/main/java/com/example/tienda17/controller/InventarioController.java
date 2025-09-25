package com.example.tienda17.controller;

import com.example.tienda17.entity.Inventario;
import com.example.tienda17.service.impl.InventarioService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inndata17/tienda")
public class InventarioController {

    @Autowired
    InventarioService inventarioService;

    @GetMapping("/inventario")
    public List<Inventario> readAll(){
        return inventarioService.readAll();
    }

    @GetMapping("/inventario/{id}")
    public Optional<Inventario> readById(@PathVariable Integer id){
        return inventarioService.ReadById(id);
    }

    @PostMapping("/inventario")
    public Inventario create(@RequestBody Inventario inventario){
        return inventarioService.create(inventario);
    }

    @PutMapping("/inventario")
    public Inventario update(@RequestBody Inventario inventario){
        return inventarioService.update(inventario);
    }

    @DeleteMapping("/inventario/{id}")
    public String delete(@PathVariable Integer id){
        return inventarioService.deleteById(id);
    }

    @GetMapping("/inventarioMayor")
    public List<Inventario> stockMayor(@PathParam("stock") Integer stock){
        return inventarioService.stockMayor(stock);
    }

}
