package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Proveedores;
import com.example.tienda17.model.ProveedoresDto;
import com.example.tienda17.repository.ProveedoresRepository;
import com.example.tienda17.service.InterProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedoresService implements InterProveedoresService {
    @Autowired
    ProveedoresRepository proveedoresRepository;

    @Override
    public List<ProveedoresDto> readAll() {
        return proveedoresRepository.findAll().stream().map(p->{
            ProveedoresDto proveedoresDto = new ProveedoresDto(p.getNombreEmpresa(),p.getContacto(),p.getTelefono());
            return proveedoresDto;
        }).toList();
    }

    @Override
    public Optional<Proveedores> readById(Integer id) {
        return proveedoresRepository.findById(id);
    }

    @Override
    public Proveedores create(Proveedores proveedores) {
        return proveedoresRepository.save(proveedores);
    }

    @Override
    public Proveedores update(Proveedores proveedores) {
        return proveedoresRepository.save((proveedores));
    }

    @Override
    public String deleteById(Integer id) {
        Optional<Proveedores> provedor = proveedoresRepository.findById(id);
        if(provedor.isPresent()){
            Proveedores proveedores = provedor.get();
            proveedores.setActivo(false);
            proveedoresRepository.save(proveedores);
            return "Proveedor con id: " + id + ". eliminado.";
        } else {
            return "Proveedor no encontrado";
        }
    }
}
