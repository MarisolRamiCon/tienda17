package com.example.tienda17.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda17.entity.DetallePedido;
import com.example.tienda17.model.PromocionesProductos;
import com.example.tienda17.service.impl.PromocionesProductosService;

@RestController
@RequestMapping("/inndata17/tienda")
public class PromocionesProductosController {
	@Autowired
	private PromocionesProductosService promocionesProductosService;
	
	@GetMapping("/promocionesProductos")
	public List<PromocionesProductos> ReadAll(){
        return promocionesProductosService.ReadAll();

	}
	@GetMapping("/promocionesProductos/{id}")
	public Optional<PromocionesProductos> readAllById(@PathVariable Integer id){
        return promocionesProductosService.ReadAllById(id);
    }
	@PostMapping("/promocionesProductos")
	public PromocionesProductos create(@RequestBody PromocionesProductos nuevo) {
        return promocionesProductosService.create(nuevo);
    }
	@PutMapping("/promocionesProductos/{id}")
	public PromocionesProductos update(@PathVariable Integer id, @RequestBody PromocionesProductos actualizado) {
        return promocionesProductosService.update(id, actualizado);
    }
	 @DeleteMapping("/promocionesProductos/{id}")
	 public ResponseEntity<String> delete(@PathVariable Integer id) {
	        promocionesProductosService.delete(id);
	        return ResponseEntity.ok("Producto eliminado correctamente");
	    }
	 //Delete logico
	 @PutMapping("/promocionesProductos/delete-logico/{id}")
	    public ResponseEntity<String> deleteLogico(@PathVariable Integer id) {
	        promocionesProductosService.deleteLogico(id);
	        return ResponseEntity.ok("Producto desactivado correctamente, borrado lógico");
	    }

}
