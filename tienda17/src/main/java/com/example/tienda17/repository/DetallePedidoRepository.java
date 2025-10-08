package com.example.tienda17.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.tienda17.entity.DetallePedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
	List<DetallePedido> findByPrecioUnitarioGreaterThan(double precioUnitario);

	List<DetallePedido> findByPrecioUnitarioGreaterThanEqual(double precioUnitario);

	// metodo personalizado con query
	@Query(value = "select * from detalles_pedidos where precio_unitario >= :precioUnitario", nativeQuery = true)
	public List<DetallePedido> findByPrecioUnitario(@Param("precioUnitario")double precio);

}
