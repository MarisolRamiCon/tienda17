package com.example.tienda17.repository;

import com.example.tienda17.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    //Metodo para obtener lista de productos mayores a x cantidad en stock
    public List<Inventario> findByStockGreaterThan(Integer stock);

    //Metodo personalizado con query nativa
    @Query(value = "SELECT * FROM inventario WHERE stock = 0", nativeQuery = true)
    public List<Inventario> productosAgotados();
}
