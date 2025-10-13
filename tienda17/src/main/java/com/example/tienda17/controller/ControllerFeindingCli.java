package com.ejemploTienda.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemploTienda.Dto.ClienteDto;
import com.ejemploTienda.Feinding.IFeinding;

@RestController
@RequestMapping("/test-clientes")

public class ControllerFeindingCli {
	 private final IFeinding iFeinding;

	    public ControllerFeindingCli(IFeinding iFeinding) {
	        this.iFeinding = iFeinding;
	    }

	    @GetMapping
	    public List<ClienteDto> obtenerClientes() {
	        return (List<ClienteDto>) iFeinding.getTodos();
	    }

	    @GetMapping("/{id}")
	    public ClienteDto obtenerPorId(@PathVariable Long id) {
	        return iFeinding.getPorId(id);
	    }
}
