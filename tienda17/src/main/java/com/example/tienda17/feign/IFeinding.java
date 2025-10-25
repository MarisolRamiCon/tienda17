package com.example.tienda17.feign;
import java.util.List;

import com.example.tienda17.dto.ClienteDto;
import com.example.tienda17.dto.ClienteRequest;
import com.example.tienda17.dto.ClienteResponse;
import com.example.tienda17.dto.DatosDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
