package com.example.tienda17.controller;

import com.example.tienda17.entity.Proveedores;
import com.example.tienda17.model.ProveedoresDto;
import com.example.tienda17.service.impl.ProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inndata17/tienda")
public class ProveedoresController {
    @Autowired
    ProveedoresService proveedoresService;

    @GetMapping("/proveedores")
    public List<ProveedoresDto> readAll(){
        return proveedoresService.readAll();
    }

    @GetMapping("/proveedores/{id}")
    public Optional<Proveedores> readById(@PathVariable Integer id){
        return proveedoresService.readById(id);
    }

    @PostMapping("/proveedores")
    public Proveedores create(@RequestBody Proveedores proveedores){
        return proveedoresService.create(proveedores);
    }

    @PutMapping("/proveedores")
    public Proveedores update(@RequestBody Proveedores proveedores){
        return proveedoresService.create(proveedores);
    }

    @DeleteMapping("/proveedores/{id}")
    public String delete(@PathVariable Integer id){
        return proveedoresService.deleteById(id);
    }
}
