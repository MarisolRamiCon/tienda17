package com.example.tienda17.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tienda17.feign.PromocionesProductosConect;
import com.example.tienda17.model.PromocionesProductos;



@Service
public class PromocionesProductosService {
	@Autowired
	private PromocionesProductosConect promocionesProductosConect;
	
	public List<PromocionesProductos> ReadAll() {
        return promocionesProductosConect.ReadAll().stream().filter(prom-> prom.isActivo()).toList();
	
	}
	
    public Optional<PromocionesProductos> ReadAllById(Integer id) {
		return promocionesProductosConect.ReadAllById(id);
	}
	
	public PromocionesProductos create(PromocionesProductos nuevo) {
	     return promocionesProductosConect.create(nuevo);
	    }
	
	public PromocionesProductos update(Integer id, PromocionesProductos actualizado) {
		System.out.println("ID recibido"+ id);
	     return promocionesProductosConect.update(id, actualizado);
	    }
	
	 public void delete(Integer id) {
	        promocionesProductosConect.delete(id);
	    }
	
	  public void deleteLogico(Integer id) {
	        PromocionesProductos producto = promocionesProductosConect.ReadAllById(id)
	                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID " + id));
	        producto.setActivo(false);
	        promocionesProductosConect.update(id, producto);
	    }
	}
	
