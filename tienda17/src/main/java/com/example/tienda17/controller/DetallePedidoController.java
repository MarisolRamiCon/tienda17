package com.example.tienda17.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda17.entity.DetallePedido;
import com.example.tienda17.model.DetalleRequest;
import com.example.tienda17.service.impl.DetallePedidoService;

@RestController
@RequestMapping("/inndata17/tienda")
public class DetallePedidoController {
	@Autowired
	DetallePedidoService detallePedidoService;

	@GetMapping("/detallePedido")
	public List<DetallePedido> readAll() {
		return detallePedidoService.readAll();

	}

	@GetMapping("/detallePedido/{id}")
	public Optional<DetallePedido> readById(@PathVariable Integer id) {
		return detallePedidoService.ReadById(id);
	}

	@PostMapping("/detallePedido")
	public DetallePedido create(@RequestBody DetallePedido detallePedido) {
		return detallePedidoService.create(detallePedido);
	}

	@PutMapping("/detallePedido/{id}")
	public DetallePedido update(@RequestBody DetallePedido detallePedido) {
		return detallePedidoService.update(detallePedido);
	}

	@DeleteMapping("/detallePedido/{id}")
	public String delete(@PathVariable Integer id) {
		return detallePedidoService.deleteById(id);
	}
//DTO
	@GetMapping("/detalle")
	public List<DetalleRequest> ReadAll() {
		return detallePedidoService.ReadAll();

	}

	@GetMapping("/detalle/{id}")
	public Optional<DetalleRequest> ReadById(@PathVariable Integer id) {
		return detallePedidoService.readById(id);
	}
	
//metodos personalizados
	
	  // Precio mayor que
    @GetMapping("/mayor/{precio}")
    public List<DetallePedido> findByPrecioUnitarioGreaterThan(@PathVariable double precio) {
        return detallePedidoService.findByPrecioUnitarioGreaterThan(precio);
    }

    // Precio mayor o igual que 
    @GetMapping("/mayorIgual/{precio}")
    public List<DetallePedido> findByPrecioUnitarioGreaterThanEqual(@PathVariable double precio) {
        return detallePedidoService.findByPrecioUnitarioGreaterThanEqual(precio);
    }
    //query
    @GetMapping("/precioMinimo/{precio}")
    public List<DetallePedido> findByPrecioUnitario(@PathVariable double precio) {
        return detallePedidoService.findByPrecioUnitario(precio);
    }
	

}
