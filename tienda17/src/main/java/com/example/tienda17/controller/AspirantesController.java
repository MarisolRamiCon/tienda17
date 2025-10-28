package com.example.tienda17.controller;

import com.example.tienda17.model.Aspirantes;
import com.example.tienda17.service.impl.AspirantesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/aspirantes")
public class AspirantesController {

    private final AspirantesServices aspirantesServices;

    public AspirantesController(AspirantesServices aspirantesServices) {
        this.aspirantesServices = aspirantesServices;
    }

    // -----------------------------
    // GET all aspirantes activos
    // -----------------------------
    @GetMapping
    public ResponseEntity<List<Aspirantes>>getAll() {
        List<Aspirantes> aspirantes = aspirantesServices.readAll();
        return ResponseEntity.ok(aspirantes);
    }

    // -----------------------------
    // GET aspirante por ID
    // -----------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Aspirantes> getById(@PathVariable String id) {
        Aspirantes aspirante = aspirantesServices.readById(id);
        if (aspirante != null) {
            return ResponseEntity.ok(aspirante);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // -----------------------------
    // POST crear nuevo aspirante
    // -----------------------------
    @PostMapping
    public ResponseEntity<Aspirantes> create(@RequestBody Aspirantes aspirante) {
        Aspirantes creado = aspirantesServices.create(aspirante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // -----------------------------
    // PUT actualizar aspirante
    // -----------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Aspirantes> update(@PathVariable String id, @RequestBody Aspirantes aspirante) {
        Aspirantes actualizado = aspirantesServices.update(id, aspirante);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // -----------------------------
    // DELETE soft delete (inactivar)
    // -----------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Aspirantes> delete(@PathVariable String id) {
        Aspirantes eliminado = aspirantesServices.delete(id);
        if (eliminado != null) {
            return ResponseEntity.ok(eliminado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

