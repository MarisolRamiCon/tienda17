package com.example.tienda17.service.impl;

import com.example.tienda17.dto.ProveedoresRequest;
import com.example.tienda17.dto.ProveedoresResponse;
import com.example.tienda17.entity.Proveedores;
import com.example.tienda17.mapper.ProveedoresMapper;
import com.example.tienda17.repository.ProveedoresRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProveedoresServiceTest {

    @Mock
    private ProveedoresRepository proveedoresRepository;

    @InjectMocks
    private ProveedoresService proveedoresService;

    @Test
    void testReadAll() {
        Proveedores p1 = new Proveedores(1, "Empresa1", "Juan", "juan@mail.com", "5512345678", true);
        Proveedores p2 = new Proveedores(2, "Empresa2", "Ana", "ana@mail.com", "5598765432", false);

        when(proveedoresRepository.findAll()).thenReturn(List.of(p1, p2));

        List<ProveedoresResponse> result = proveedoresService.readAll();

        assertEquals(1, result.size());
        assertEquals("Empresa1", result.get(0).getNombreEmpresa());
        verify(proveedoresRepository, times(1)).findAll();
    }

    @Test
    void testReadById() {
        Proveedores p = new Proveedores(1, "Empresa1", "Juan", "juan@mail.com", "5512345678", true);

        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(p));

        Optional<ProveedoresResponse> result = proveedoresService.readById(1);

        assertTrue(result.isPresent());
        assertEquals("Empresa1", result.get().getNombreEmpresa());
        verify(proveedoresRepository, times(1)).findById(1);
    }

    @Test
    void testCreate() {
        ProveedoresRequest request = new ProveedoresRequest("Empresa1", "Juan", "juan@mail.com", "5512345678");
        Proveedores entity = ProveedoresMapper.toEntity(request);
        Proveedores saved = new Proveedores(1, "Empresa1", "Juan", "juan@mail.com", "5512345678", true);

        when(proveedoresRepository.save(entity)).thenReturn(saved);

        ProveedoresResponse result = proveedoresService.create(request);

        assertNotNull(result);
        assertEquals("Empresa1", result.getNombreEmpresa());
        verify(proveedoresRepository, times(1)).save(entity);
    }

    @Test
    void testUpdate() {
        ProveedoresRequest request = new ProveedoresRequest("EmpresaActualizada", "Ana", "ana@mail.com", "5598765432");
        Proveedores existing = new Proveedores(1, "Empresa1", "Juan", "juan@mail.com", "5512345678", true);
        Proveedores updated = new Proveedores(1, "EmpresaActualizada", "Ana", "ana@mail.com", "5598765432", true);

        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(existing));
        when(proveedoresRepository.save(existing)).thenReturn(updated);

        ProveedoresResponse result = proveedoresService.update(1, request);

        assertEquals("EmpresaActualizada", result.getNombreEmpresa());
        verify(proveedoresRepository, times(1)).findById(1);
        verify(proveedoresRepository, times(1)).save(existing);
    }

    @Test
    void testDeleteById() {
        Proveedores existing = new Proveedores(1, "Empresa1", "Juan", "juan@mail.com", "5512345678", true);
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(existing));
        when(proveedoresRepository.save(existing)).thenReturn(existing);

        String result = proveedoresService.deleteById(1);

        assertEquals("Proveedor con id 1 dado de baja", result);
        assertFalse(existing.getActivo());
        verify(proveedoresRepository, times(1)).findById(1);
        verify(proveedoresRepository, times(1)).save(existing);
    }
}
