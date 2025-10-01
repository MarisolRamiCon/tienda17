package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Inventario;
import com.example.tienda17.repository.InventarioRepository;
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

class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll() {
        Inventario producto1 = new Inventario(1, 101, 10);
        Inventario producto2 = new Inventario(2, 102, 5);

        when(inventarioRepository.findAll()).thenReturn(List.of(producto1, producto2));

        List<Inventario> result = inventarioService.readAll();

        assertEquals(2, result.size());
        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    void testReadById() {
        Inventario producto = new Inventario(1, 101, 10);

        when(inventarioRepository.findById(1)).thenReturn(Optional.of(producto));

        Optional<Inventario> result = inventarioService.ReadById(1);

        assertTrue(result.isPresent());
        assertEquals(101, result.get().getProducto());
        assertEquals(10, result.get().getStock());
        verify(inventarioRepository, times(1)).findById(1);
    }

    @Test
    void testCreate() {
        Inventario producto = new Inventario(1, 101, 10);

        when(inventarioRepository.save(producto)).thenReturn(producto);

        Inventario result = inventarioService.create(producto);

        assertNotNull(result);
        assertEquals(producto.getProducto(), result.getProducto());
        verify(inventarioRepository, times(1)).save(producto);
    }

    @Test
    void testUpdate() {
        Inventario producto = new Inventario(1, 101, 15);

        when(inventarioRepository.save(producto)).thenReturn(producto);

        Inventario result = inventarioService.update(producto);

        assertNotNull(result);
        assertEquals(15, result.getStock());
        verify(inventarioRepository, times(1)).save(producto);
    }

    @Test
    void testDeleteById() {
        doNothing().when(inventarioRepository).deleteById(1);

        String result = inventarioService.deleteById(1);

        assertEquals("Registro con ID 1 eliminado", result);
        verify(inventarioRepository, times(1)).deleteById(1);
    }

    @Test
    void testStockMayor() {
        Inventario producto1 = new Inventario(1, 101, 20);
        Inventario producto2 = new Inventario(2, 102, 15);

        when(inventarioRepository.findByStockGreaterThan(10)).thenReturn(List.of(producto1, producto2));

        List<Inventario> result = inventarioService.stockMayor(10);

        assertEquals(2, result.size());
        verify(inventarioRepository, times(1)).findByStockGreaterThan(10);
    }

    @Test
    void testProductosAgotados() {
        Inventario producto = new Inventario(3, 103, 0);

        when(inventarioRepository.productosAgotados()).thenReturn(List.of(producto));

        List<Inventario> result = inventarioService.productosAgotados();

        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getStock());
        verify(inventarioRepository, times(1)).productosAgotados();
    }

    @Test
    void testReadByIdSuccess() {
        Inventario producto = new Inventario(1, 101, 10);

        when(inventarioRepository.findById(1)).thenReturn(Optional.of(producto));

        Inventario result = inventarioService.ReadById(1).orElseThrow(() ->
                new NoSuchElementException("Inventario no encontrado con id: 1"));

        assertNotNull(result);
        assertEquals(101, result.getProducto());
        assertEquals(10, result.getStock());
    }

    @Test
    void testReadByIdNotFound() {
        when(inventarioRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            inventarioService.ReadById(99).orElseThrow(() ->
                    new NoSuchElementException("Inventario no encontrado con id: 99"));
        });

        assertEquals("Inventario no encontrado con id: 99", exception.getMessage());
    }

}
