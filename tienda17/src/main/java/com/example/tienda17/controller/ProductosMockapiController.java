package com.example.tienda17.controller;

import com.example.tienda17.model.ProductosMockapi;
import com.example.tienda17.service.impl.ProductosMockapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/productosmockapi")

public class ProductosMockapiController {
    @Autowired
    ProductosMockapiService productosMockapiService;
    @GetMapping("/allproductos")
    List<ProductosMockapi> ReadAll(){
        return productosMockapiService.ReadAll();
    }
    @GetMapping("allproductos/{id}")
    ProductosMockapi ReadById(@PathVariable Integer id){
        return productosMockapiService.ReadById(id);
    }
    @PostMapping("createproducto")
    String create(@RequestBody ProductosMockapi productosMockapi){
        String c = "Se creo el registro nuevo: ";
        return c + productosMockapiService.create(productosMockapi);
    }
    @PutMapping("actualizarproducto/{id}")
    String update(@RequestBody ProductosMockapi productosMockapi, @PathVariable Integer id){
        productosMockapiService.update(id, productosMockapi);
        String a = "Se actualiza el registro: ";
        return a + id;
    }
    @DeleteMapping("eliminarproducto/{id}")
    String deleteLogico(@PathVariable Integer id){
        return productosMockapiService.deleteLogico(id);
    }
}
