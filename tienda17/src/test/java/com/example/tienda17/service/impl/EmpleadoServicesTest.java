package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Empleado;
import com.example.tienda17.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmpleadoServicesTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServices empleadoServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll() {
        Empleado e1 = new Empleado(1, "Juan", "Pérez", "Ventas", 2000.0, null, true);
        Empleado e2 = new Empleado(2, "Ana", "Lopez", "Asistente", 2500.0, null, true);

        when(empleadoRepository.findAll()).thenReturn(List.of(e1, e2));

        List<Empleado> result = empleadoServices.readAll();

        assertEquals(2, result.size());
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void testReadByIdFound() {
        Empleado e = new Empleado(1, "Juan", "Pérez", "Ventas", 2000.0, null, true);
        when(empleadoRepository.findById(1)).thenReturn(Optional.of(e));

        Optional<Empleado> result = empleadoServices.ReadById(1);

        assertTrue(result.isPresent());
        assertEquals("Juan", result.get().getNombre());
    }

    @Test
    void testReadByIdNotFound() {
        when(empleadoRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Empleado> result = empleadoServices.ReadById(99);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreate() {
        Empleado e = new Empleado(1, "Juan", "Pérez", "Ventas", 2000.0, null, true);
        when(empleadoRepository.save(e)).thenReturn(e);

        Empleado result = empleadoServices.create(e);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(empleadoRepository, times(1)).save(e);
    }

    @Test
    void testUpdate() {
        Empleado e = new Empleado(1, "Juan", "Pérez", "Ventas", 2000.0, null, true);
        when(empleadoRepository.save(e)).thenReturn(e);

        Empleado result = empleadoServices.update(e);

        assertEquals(2000.0, result.getSalario());
        verify(empleadoRepository, times(1)).save(e);
    }

    @Test
    void testDeleteByIdSuccess() {
        Empleado e = new Empleado(1, "Juan", "Pérez", "Ventas", 2000.0, null, true);
        when(empleadoRepository.findById(1)).thenReturn(Optional.of(e));
        when(empleadoRepository.save(e)).thenReturn(e);

        empleadoServices.deleteById(1);

        assertFalse(e.isActivo());
        verify(empleadoRepository, times(1)).save(e);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(empleadoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> empleadoServices.deleteById(99));
    }

    @Test
    void testFindByActivoTrue() {
        Empleado e1 = new Empleado(1, "Juan", "Pérez", "Ventas", 2000.0, null, true);
        when(empleadoRepository.empleadosActivos()).thenReturn(List.of(e1));

        List<Empleado> result = empleadoServices.findByActivoTrue();

        assertEquals(1, result.size());
        assertTrue(result.get(0).isActivo());
        verify(empleadoRepository, times(1)).empleadosActivos();
    }

    @Test
    void testFindBySalarioGreaterThan() {
        Empleado e1 = new Empleado(1, "Juan", "Pérez", "Ventas", 3000.0, null, true);
        when(empleadoRepository.empleadosConSalarioMayorA(2500)).thenReturn(List.of(e1));

        List<Empleado> result = empleadoServices.findBySalarioGreaterThan(2500);

        assertEquals(1, result.size());
        assertTrue(result.get(0).getSalario() > 2500);
        verify(empleadoRepository, times(1)).empleadosConSalarioMayorA(2500);
    }
}
