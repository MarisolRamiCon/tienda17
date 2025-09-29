package com.example.tienda17.service;

import com.example.tienda17.entity.Productos;
import com.example.tienda17.model.ProductosDto;

import java.util.List;
import java.util.Optional;

public interface IProductosService {
    public List<Productos> ReadAll();
    public Optional<Productos> ReadById(Integer idproducto);
    public Productos create(Productos productos);
    public Productos update(Productos productos);
    public String deleteById(Integer idproducto);
    public List<Productos> precioMayor(double prodprecio);
    public List<Productos> precioMayorquery(double prodprecio);
    public List<ProductosDto> readAll();
}
