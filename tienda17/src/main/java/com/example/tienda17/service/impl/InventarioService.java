package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Inventario;
import com.example.tienda17.repository.InventarioRepository;
import com.example.tienda17.service.InterInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService implements InterInventarioService {
    @Autowired
    InventarioRepository inventarioRepository;

    public List<Inventario> readAll(){
        return inventarioRepository.findAll();
    }

    @Override
    public Optional<Inventario> ReadById(Integer id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public Inventario create(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Inventario update(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public String deleteById(Integer id) {
        inventarioRepository.deleteById(id);
        return "Registro con ID " + id + " eliminado";
    }

    @Override
    public List<Inventario> stockMayor(Integer stock) {
        return inventarioRepository.findByStockGreaterThan(stock);
    }


}
