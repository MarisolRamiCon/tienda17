package com.example.tienda17.repository;

import com.example.tienda17.dto.ProveedoresResponse;
import com.example.tienda17.entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {
    //Metodo personalizado con query nativa
    @Query(value = "SELECT nombre_empresa AS nombreEmpresa, contacto, telefono " +
            "FROM proveedores WHERE activo = 0", nativeQuery = true)
    public List<ProveedoresResponse> proveedoresBaja();

    List<Proveedores> findByContactoContaining(String texto);
}
