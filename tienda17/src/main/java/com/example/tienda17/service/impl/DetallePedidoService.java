package com.example.tienda17.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.tienda17.entity.DetallePedido;
import com.example.tienda17.exceptions.ResourceNotFoundException;
import com.example.tienda17.model.DetalleRequest;
import com.example.tienda17.repository.DetallePedidoRepository;
import com.example.tienda17.service.InterDetallePedidoService;

@Service
public class DetallePedidoService implements InterDetallePedidoService{
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Override
	public List<DetallePedido> readAll(){
		try {
			return detallePedidoRepository.findAll()
					.stream()
					.filter(det-> det.getActivo())
					.toList();
		}catch(Exception e){
			throw new ServiceException("Error al obtener los detalles del pedido", e);
			
		}	
	}
	@Override
    public Optional<DetallePedido> ReadById(Integer id) {
        
        try {
        	return detallePedidoRepository.findById(id);
		}catch(Exception e){
			throw new ServiceException("Error al buscar el detalle del pedido por ID" + id, e);
			
		}	
    }

	@Override
    public DetallePedido create(DetallePedido detallePedido) {
        try {
            return detallePedidoRepository.save(detallePedido);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Error de integridad al crear el detalle de pedido", e);
        } catch (Exception e) {
            throw new ServiceException("Error al crear el detalle de pedido", e);
        }
    }

    @Override
    public DetallePedido update(DetallePedido detallePedido) {
        try {
            return detallePedidoRepository.save(detallePedido);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el detalle de pedido", e);
        }
    }
    
    @Override
    public String deleteById(Integer id) {
        try {
            Optional<DetallePedido> detallePedidoBuscado = detallePedidoRepository.findById(id);
            if (detallePedidoBuscado.isPresent()) {
                DetallePedido detallePedido = detallePedidoBuscado.get();
                detallePedido.setActivo(false);
                detallePedidoRepository.save(detallePedido);
                return "Detalle pedido desactivado correctamente";
            } else {
                throw new ResourceNotFoundException("DetallePedido con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el detalle de pedido con ID: " + id, e);
        } finally {
            
            System.out.println("Operación deleteById finalizada para ID: " + id);
        }
    }
    @Override
    public List<DetalleRequest> ReadAll(){
    	try {
    		return detallePedidoRepository.findAll()
    				.stream()
    				.map(
        			detalle -> {
        				DetalleRequest detalleRequest = new DetalleRequest(
        						detalle.getPedido(),detalle.getProducto(),detalle.getCantidad()
        						);
        				return detalleRequest;
        			}
        			).toList();
		}catch(Exception e){
			throw new ServiceException("Error al obtener los detalles del pedido", e);
			
		}
    }
    @Override
    public Optional<DetalleRequest>readById(Integer id) {
        
        try {
        	return detallePedidoRepository.findById(id)
                    .map(detalle -> new DetalleRequest(
                            detalle.getPedido(),
                            detalle.getProducto(), 
                            detalle.getCantidad()
                    ));
        	
        }catch(Exception e){
        	throw new ServiceException("Error al buscar el detalle del pedido por ID" + id, e);
        	
        }
    }
    //metodos personalizados
    
    public List<DetallePedido> findByPrecioUnitarioGreaterThan(double precio) {
        return detallePedidoRepository.findByPrecioUnitarioGreaterThan(precio);
    }

    public List<DetallePedido> findByPrecioUnitarioGreaterThanEqual(double precio) {
        return detallePedidoRepository.findByPrecioUnitarioGreaterThanEqual(precio);
    }
    //query
    //Llamamos al método personalizado del repository
    public List<DetallePedido> findByPrecioUnitario(double precio) {
        return detallePedidoRepository.findByPrecioUnitario(precio); 
    }
}
