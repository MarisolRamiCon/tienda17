package inndata172.ejercicioTarea.service.impl;

import inndata172.ejercicioTarea.entity.Departamento;
import inndata172.ejercicioTarea.repository.DepartamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartamentoServiceTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DepartamentoService departamentoService;

    private Departamento departamentoActivo;
    private Departamento departamentoInactivo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        departamentoActivo = new Departamento();
        departamentoActivo.setIddepartamento(1);
        departamentoActivo.setDnombre("Ventas");
        departamentoActivo.setEliminarlogico(true);
        departamentoActivo.setPrecio(1000.0);
        departamentoActivo.setM2("50");

        departamentoInactivo = new Departamento();
        departamentoInactivo.setIddepartamento(2);
        departamentoInactivo.setDnombre("Marketing");
        departamentoInactivo.setEliminarlogico(false);
        departamentoInactivo.setPrecio(2000.0);
        departamentoInactivo.setM2("60");
    }

    @Test
    void testReadAll() {
        when(departamentoRepository.findAll()).thenReturn(Arrays.asList(departamentoActivo, departamentoInactivo));
        List<Departamento> result = departamentoService.ReadAll();
        assertEquals(1, result.size());
        assertTrue(result.get(0).getEliminarlogico());
        verify(departamentoRepository, times(1)).findAll();
    }

    @Test
    void testReadById() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.of(departamentoActivo));
        Optional<Departamento> result = departamentoService.ReadById(1);
        assertTrue(result.isPresent());
        assertEquals("Ventas", result.get().getDnombre());
        verify(departamentoRepository, times(1)).findById(1);
    }

    @Test
    void testCreate() {
        when(departamentoRepository.save(departamentoActivo)).thenReturn(departamentoActivo);
        Departamento result = departamentoService.create(departamentoActivo);
        assertNotNull(result);
        assertEquals("Ventas", result.getDnombre());
        verify(departamentoRepository, times(1)).save(departamentoActivo);
    }

    @Test
    void testUpdate() {
        when(departamentoRepository.save(departamentoActivo)).thenReturn(departamentoActivo);
        Departamento result = departamentoService.update(departamentoActivo);
        assertEquals("Ventas", result.getDnombre());
        verify(departamentoRepository, times(1)).save(departamentoActivo);
    }

    @Test
    void testDeleteById_DepartamentoEncontrado() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.of(departamentoActivo));
        String result = departamentoService.deleteById(1);
        assertEquals("Departamento borrado", result);
        assertFalse(departamentoActivo.getEliminarlogico());
        verify(departamentoRepository, times(1)).findById(1);
        verify(departamentoRepository, times(1)).save(departamentoActivo);
    }

    @Test
    void testDeleteById_DepartamentoNoEncontrado() {
        when(departamentoRepository.findById(99)).thenReturn(Optional.empty());
        String result = departamentoService.deleteById(99);
        assertEquals("Departamento no encontrado", result);
        verify(departamentoRepository, times(1)).findById(99);
        verify(departamentoRepository, never()).save(any());
    }

    @Test
    void testPrecioMayor() {
        when(departamentoRepository.findByPrecioGreaterThan(1500.0))
                .thenReturn(Arrays.asList(departamentoInactivo));
        List<Departamento> result = departamentoService.precioMayor(1500.0);
        assertEquals(1, result.size());
        assertEquals("Marketing", result.get(0).getDnombre());
        verify(departamentoRepository, times(1)).findByPrecioGreaterThan(1500.0);
    }

    @Test
    void testPrecioM2() {
        when(departamentoRepository.m2AndPrecio(50, 900.0))
                .thenReturn(Arrays.asList(departamentoActivo, departamentoInactivo));
        List<Departamento> result = departamentoService.precioM2("50", 900.0);
        assertEquals(1, result.size());
        assertEquals("Ventas", result.get(0).getDnombre());
        verify(departamentoRepository, times(1)).m2AndPrecio(50, 900.0);
    }
}