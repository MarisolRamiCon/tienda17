package com.example.tienda17.controller;

import com.example.tienda17.entity.Inventario;
import com.example.tienda17.service.impl.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inndata17/tienda")
public class InventarioController {
    @Autowired
    InventarioService inventarioService;
    @GetMapping("/inventario")
    public List<Inventario> readAll(){
        return inventarioService.readAll();
    }
}
