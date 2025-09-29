package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Inventario;
import com.example.tienda17.repository.InventarioRepository;
import com.example.tienda17.service.InterInventarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService implements InterInventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> readAll(){
        return inventarioRepository.findAll();
    }
}
