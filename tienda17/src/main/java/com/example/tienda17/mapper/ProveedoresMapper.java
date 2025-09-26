package com.example.tienda17.mapper;

import com.example.tienda17.dto.ProveedoresRequest;
import com.example.tienda17.dto.ProveedoresResponse;
import com.example.tienda17.entity.Proveedores;

public class ProveedoresMapper {
    // Entity -> Response
    public static ProveedoresResponse toResponse(Proveedores entity) {
        return new ProveedoresResponse(
                entity.getNombreEmpresa(),
                entity.getContacto(),
                entity.getTelefono()
        );
    }

    // Request -> Entity
    public static Proveedores toEntity(ProveedoresRequest request) {
        Proveedores entity = new Proveedores();
        entity.setNombreEmpresa(request.getNombreEmpresa());
        entity.setContacto(request.getContacto());
        entity.setEmail(request.getEmail());
        entity.setTelefono(request.getTelefono());
        entity.setActivo(true);
        return entity;
    }
}
