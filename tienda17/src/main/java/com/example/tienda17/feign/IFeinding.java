package com.ejemploTienda.Feinding;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ejemploTienda.Dto.ClienteDto;
import com.ejemploTienda.Dto.ClienteRequest;
import com.ejemploTienda.Dto.ClienteResponse;
import com.ejemploTienda.Dto.DatosDto;

@FeignClient(name = "servicio-clientes", url = "http://localhost:6060")
public interface IFeinding {

    @GetMapping("/api/clientes")
	static
    List<ClienteDto> getTodos() {
		// TODO Auto-generated method stub
		return null;
	} 

    @GetMapping("/api/clientes/{id}")
    ClienteDto getPorId(@PathVariable Long id);

    @PostMapping("/api/clientes")
    ClienteResponse crear(@RequestBody ClienteRequest cliente);

    @DeleteMapping("/api/clientes/{id}")
    void eliminar(@PathVariable Long id);

    @GetMapping("/api/clientes/activos/nombre-correo")
    List<DatosDto> getClientesNombreCorreo();
}