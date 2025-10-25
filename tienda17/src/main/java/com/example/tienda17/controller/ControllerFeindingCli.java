package com.example.tienda17.controller;
import java.util.List;

import com.example.tienda17.dto.ClienteDto;
import com.example.tienda17.feign.IFeinding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-clientes")

public class ControllerFeindingCli {
    private final IFeinding iFeinding;

    public ControllerFeindingCli(IFeinding iFeinding) {
        this.iFeinding = iFeinding;
    }

    @GetMapping
    public List<ClienteDto> obtenerClientes() {
        return (List<ClienteDto>) IFeinding.getTodos();
    }

    @GetMapping("/{id}")
    public ClienteDto obtenerPorId(@PathVariable Long id) {
        return iFeinding.getPorId(id);
    }
}
