package com.example.tienda17.repository;


import com.example.tienda17.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductosRepository extends JpaRepository<Productos, Integer> {
    public List<Productos> findByProdprecioGreaterThan(double prodprecio);
    @Query(value = "select * from productos where prodprecio>=:prodprecio", nativeQuery = true)
    public List<Productos> precioMayorquery(double prodprecio);
}
