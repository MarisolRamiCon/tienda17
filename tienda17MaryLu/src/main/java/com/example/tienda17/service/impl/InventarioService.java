package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Inventario;
import com.example.tienda17.repository.InventarioRepository;
import com.example.tienda17.service.InterInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService implements InterInventarioService {
    @Autowired
    InventarioRepository inventarioRepository;

    public List<Inventario> readAll(){
        return inventarioRepository.findAll();
    }
}
