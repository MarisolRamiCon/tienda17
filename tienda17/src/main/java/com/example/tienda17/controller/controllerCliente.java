package com.ejemploTienda.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemploTienda.Dto.ClienteDto;
import com.ejemploTienda.Dto.ClienteRequest;
import com.ejemploTienda.Dto.ClienteResponse;
import com.ejemploTienda.Dto.DatosDto;
import com.ejemploTienda.Entity.ClienteEntity;
import com.ejemploTienda.Service.ClienteService;

@RestController
@RequestMapping("/api/clientes")  
public class controllerCliente {

	 @Autowired
	    private ClienteService clienteService;

	    // Obtener todos los clientes activos
	    @GetMapping
	    public ResponseEntity<List<ClienteResponse>> getAll() {
	        List<ClienteResponse> clientes = clienteService.ReadAll();
	        return ResponseEntity.ok(clientes);
	    }

	    // Obtener cliente por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<ClienteDto> getById(@PathVariable Long id) {
	        try {
	            ClienteDto cliente = clienteService.getById(id);
	            return ResponseEntity.ok(cliente);
	        } catch (RuntimeException e) {
	            // Aquí puedes mejorar el manejo, por ejemplo, detectando si es "Cliente no encontrado"
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }

	    // Crear un nuevo cliente
	    @PostMapping
	    public ResponseEntity<ClienteResponse> create(@RequestBody ClienteRequest request) {
	        ClienteResponse nuevoCliente = clienteService.create(request);
	        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
	    }

	    // Borrado lógico de cliente
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteLogico(@PathVariable Long id) {
	        clienteService.deleteLogico(id);
	        return ResponseEntity.noContent().build();
	    }
        //Query para correo
	    public controllerCliente(ClienteService clienteService) {
	        this.clienteService = clienteService;
	    }

	    @GetMapping("/activos/nombre-correo")
	    public ResponseEntity<List<DatosDto>> getClientesNombreCorreo() {
	        return ResponseEntity.ok(clienteService.obtenerClientesActivosNombreYCorreo());
	    }
	 
	}
