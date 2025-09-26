package com.example.tienda17.service;

import com.example.tienda17.entity.Inventario;

import java.util.List;
import java.util.Optional;

public interface InterInventarioService {
    public List<Inventario> readAll();
    public Optional<Inventario> ReadById(Integer id);
    public Inventario create(Inventario inventario);
    public Inventario update(Inventario inventario);
    public String deleteById(Integer id);
    public List<Inventario> stockMayor(Integer stock);
    public List<Inventario> productosAgotados();
}
