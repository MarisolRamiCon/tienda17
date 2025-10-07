package com.example.tienda17.service.impl;

import com.example.tienda17.dto.ProveedoresRequest;
import com.example.tienda17.dto.ProveedoresResponse;
import com.example.tienda17.entity.Proveedores;
import com.example.tienda17.mapper.ProveedoresMapper;
import com.example.tienda17.repository.ProveedoresRepository;
import com.example.tienda17.service.InterProveedoresService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProveedoresService implements InterProveedoresService {

    private final ProveedoresRepository proveedoresRepository;

    public ProveedoresService(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }


    @Override
    public List<ProveedoresResponse> readAll() {
        List<Proveedores> proveedores = proveedoresRepository.findAll();
        if (proveedores.isEmpty()) {
            throw new NoSuchElementException("No se encontraron proveedores");
        }
        return proveedores.stream()
                .filter(Proveedores::getActivo)
                .map(ProveedoresMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<ProveedoresResponse> readById(Integer id) {
        return proveedoresRepository.findById(id)
                .map(ProveedoresMapper::toResponse);
    }

    @Override
    public ProveedoresResponse create(ProveedoresRequest request) {
        Proveedores entity = ProveedoresMapper.toEntity(request);
        Proveedores saved = proveedoresRepository.save(entity);
        return ProveedoresMapper.toResponse(saved);
    }

    @Override
    public ProveedoresResponse update(Integer id, ProveedoresRequest request) {
        Proveedores entity = proveedoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        entity.setNombreEmpresa(request.getNombreEmpresa());
        entity.setContacto(request.getContacto());
        entity.setEmail(request.getEmail());
        entity.setTelefono(request.getTelefono());

        Proveedores updated = proveedoresRepository.save(entity);
        return ProveedoresMapper.toResponse(updated);
    }

    @Override
    public String deleteById(Integer id) {
        Optional<Proveedores> proveedorOpt = proveedoresRepository.findById(id);
        if (proveedorOpt.isPresent()) {
            Proveedores proveedor = proveedorOpt.get();
            proveedor.setActivo(false);          // marcamos como inactivo
            proveedoresRepository.save(proveedor); // guardamos el cambio
            return "Proveedor con id " + id + " dado de baja";
        } else {
            return "Proveedor no encontrado";
        }
    }

    @Override
    public List<ProveedoresResponse> findByBaja() {
        return proveedoresRepository.proveedoresBaja();
    }

    @Override
    public List<ProveedoresResponse> findByContacto(String contacto) {
        List<Proveedores> proveedores = proveedoresRepository.findByContactoContaining(contacto);
        if (proveedores.isEmpty()) {
            throw new NoSuchElementException("No se encontraron proveedores");
        }
        return proveedores.stream()
                .map(ProveedoresMapper::toResponse)
                .toList();
    }
}
