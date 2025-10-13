package com.ejemploTienda.Repository;


import java.util.List;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejemploTienda.Dto.DatosDto;
import com.ejemploTienda.Entity.ClienteEntity;

@Repository

public interface RepositoryCliente extends JpaRepository<ClienteEntity,Long> {
	 Optional<ClienteEntity> findByNombre(String nombre);
	 List<ClienteEntity> findByEstadoTrue(); 
	 
	 @Query("SELECT c.nombre AS nombre , c.correo AS correo  FROM ClienteEntity c WHERE c.estado = true")
	 List<DatosDto> listarClientesActivosNombreYCorreo();
}
