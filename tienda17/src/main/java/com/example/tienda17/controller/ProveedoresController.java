package com.example.tienda17.controller;

import com.example.tienda17.dto.ProveedoresRequest;
import com.example.tienda17.dto.ProveedoresResponse;
import com.example.tienda17.service.impl.ProveedoresService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/inndata17/tienda")
public class ProveedoresController {
    private final ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @GetMapping("/proveedores")
    public List<ProveedoresResponse> readAll() {
        return proveedoresService.readAll();
    }

    @GetMapping("/proveedores/{id}")
    public ProveedoresResponse readById(@PathVariable Integer id) {
        return proveedoresService.readById(id)
                .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con id: " + id));
    }


    @PostMapping("/proveedores")
    public ProveedoresResponse create(@RequestBody ProveedoresRequest request) {
        return proveedoresService.create(request);
    }

    @PutMapping("/proveedores/{id}")
    public ProveedoresResponse update(@PathVariable Integer id,
                                      @RequestBody ProveedoresRequest request) {
        return proveedoresService.update(id, request);
    }

    @DeleteMapping("/proveedores/{id}")
    public String delete(@PathVariable Integer id) {
        return proveedoresService.deleteById(id); // baja lógica
    }

    @GetMapping("/proveedores/baja")
    public List<ProveedoresResponse> findByBaja() {
        return proveedoresService.findByBaja();
    }

    @GetMapping("/proveedores/contacto/{contacto}")
    public List<ProveedoresResponse> findByContacto(@PathVariable String contacto) {
        return proveedoresService.findByContacto(contacto);
    }
}
