package com.example.tienda17.service.impl;

import com.example.tienda17.feign.IAspirante;
import com.example.tienda17.model.Aspirantes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AspirantesServices {

    private final IAspirante iAspirante;

    public AspirantesServices(IAspirante iAspirante) {
        this.iAspirante = iAspirante;
    }

    public List<Aspirantes> readAll() {
        return iAspirante.readAll().stream()
                .filter(Aspirantes::getActivo) // mejor método referencia
                .toList();
    }

    public Aspirantes readById(String id) {
        return iAspirante.readById(id);
    }

    public Aspirantes create(Aspirantes aspirantesspirante){
        return iAspirante.create(aspirantesspirante);
    }

    public Aspirantes update(String id, Aspirantes aspirantes) {
        return iAspirante.update(id, aspirantes);
    }

    public Aspirantes delete(String id) {
        Aspirantes aspirante = iAspirante.readById(id);
        if (aspirante == null) {
            throw new IllegalArgumentException("Aspirante no encontrado con id: " + id);
        }
        aspirante.setActivo(false);
        return iAspirante.update(id, aspirante);
    }

    }

