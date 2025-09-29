package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Inventario;
import com.example.tienda17.repository.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    private Inventario inventario1;
    private Inventario inventario2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        inventario1 = new Inventario();
        inventario1.setId(1);
        inventario1.setProducto(101);
        inventario1.setStock(50);

        inventario2 = new Inventario();
        inventario2.setId(2);
        inventario2.setProducto(202);
        inventario2.setStock(30);
    }

    @Test
    void testReadAllReturnsInventarios() {
        when(inventarioRepository.findAll()).thenReturn(Arrays.asList(inventario1, inventario2));

        List<Inventario> result = inventarioService.readAll();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(101, result.get(0).getProducto());
        assertEquals(50, result.get(0).getStock());

        assertEquals(202, result.get(1).getProducto());
        assertEquals(30, result.get(1).getStock());

        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    void testReadAllEmptyList() {
        when(inventarioRepository.findAll()).thenReturn(List.of());

        List<Inventario> result = inventarioService.readAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(inventarioRepository, times(1)).findAll();
    }
}
