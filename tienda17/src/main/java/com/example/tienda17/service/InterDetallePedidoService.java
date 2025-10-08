package com.example.tienda17.service;

import java.util.List;
import java.util.Optional;

import com.example.tienda17.entity.DetallePedido;
import com.example.tienda17.model.DetalleRequest;

public interface InterDetallePedidoService {
	public List<DetallePedido> readAll();
	public Optional<DetallePedido> ReadById(Integer id);
    public DetallePedido create(DetallePedido detallePedido);
    public DetallePedido update(DetallePedido detallePedido);
    public String deleteById(Integer id);
    
    public List<DetalleRequest> ReadAll();
    public Optional<DetalleRequest> readById(Integer id);

}
