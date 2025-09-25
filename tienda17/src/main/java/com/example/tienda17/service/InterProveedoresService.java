package com.example.tienda17.service;

import com.example.tienda17.entity.Proveedores;
import com.example.tienda17.model.ProveedoresDto;

import java.util.List;
import java.util.Optional;

public interface InterProveedoresService {
    public List<ProveedoresDto> readAll();
    public Optional<Proveedores> readById(Integer id);
    public Proveedores create(Proveedores proveedores);
    public Proveedores update(Proveedores proveedores);
    public String deleteById(Integer id);
}
