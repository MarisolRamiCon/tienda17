package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Productos;
import com.example.tienda17.model.ProductosDto;
import com.example.tienda17.repository.IProductosRepository;
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

class ProductosServiceTest {

    @Mock
    private IProductosRepository productosRepository;

    @InjectMocks
    private ProductosService productosService;

    private Productos productoActivo;
    private Productos productoInactivo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productoActivo = new Productos();
        productoActivo.setIdproducto(1);
        productoActivo.setProdnombre("Laptop");
        productoActivo.setProdprecio(1500.0);
        productoActivo.setActivo(true);

        productoInactivo = new Productos();
        productoInactivo.setIdproducto(2);
        productoInactivo.setProdnombre("Mouse");
        productoInactivo.setProdprecio(300.0);
        productoInactivo.setActivo(false);
    }

    @Test
    void testReadAll() {
        when(productosRepository.findAll()).thenReturn(Arrays.asList(productoActivo, productoInactivo));

        List<Productos> result = productosService.ReadAll();

        assertEquals(1, result.size());
        assertTrue(result.get(0).getActivo());
        verify(productosRepository, times(1)).findAll();
    }

    @Test
    void testReadByIdFound() {
        when(productosRepository.findById(1)).thenReturn(Optional.of(productoActivo));

        Optional<Productos> result = productosService.ReadById(1);

        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getProdnombre());
        verify(productosRepository, times(1)).findById(1);
    }

    @Test
    void testReadByIdNotFound() {
        when(productosRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Productos> result = productosService.ReadById(99);

        assertFalse(result.isPresent());
        verify(productosRepository, times(1)).findById(99);
    }

    @Test
    void testCreate() {
        when(productosRepository.save(productoActivo)).thenReturn(productoActivo);

        Productos result = productosService.create(productoActivo);

        assertNotNull(result);
        assertEquals("Laptop", result.getProdnombre());
        verify(productosRepository, times(1)).save(productoActivo);
    }

    @Test
    void testUpdate() {
        when(productosRepository.save(productoActivo)).thenReturn(productoActivo);

        Productos result = productosService.update(productoActivo);

        assertNotNull(result);
        assertEquals("Laptop", result.getProdnombre());
        verify(productosRepository, times(1)).save(productoActivo);
    }

    @Test
    void testDeleteByIdFound() {
        when(productosRepository.findById(1)).thenReturn(Optional.of(productoActivo));
        when(productosRepository.save(productoActivo)).thenReturn(productoActivo);

        String result = productosService.deleteById(1);

        assertEquals("Producto eliminado: 1", result);
        assertFalse(productoActivo.getActivo());
        verify(productosRepository, times(1)).findById(1);
        verify(productosRepository, times(1)).save(productoActivo);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(productosRepository.findById(99)).thenReturn(Optional.empty());

        String result = productosService.deleteById(99);

        assertEquals("Registro eliminado no encontrado", result);
        verify(productosRepository, times(1)).findById(99);
        verify(productosRepository, never()).save(any(Productos.class));
    }

    @Test
    void testPrecioMayor() {
        when(productosRepository.findByProdprecioGreaterThan(1000.0))
                .thenReturn(List.of(productoActivo));

        List<Productos> result = productosService.precioMayor(1000.0);

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getProdnombre());
        verify(productosRepository, times(1)).findByProdprecioGreaterThan(1000.0);
    }

    @Test
    void testPrecioMayorQuery() {
        when(productosRepository.precioMayorquery(200.0))
                .thenReturn(List.of(productoActivo));

        List<Productos> result = productosService.precioMayorquery(200.0);

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getProdnombre());
        verify(productosRepository, times(1)).precioMayorquery(200.0);
    }

    @Test
    void testReadAllDto() {
        when(productosRepository.findAll()).thenReturn(Arrays.asList(productoActivo));

        List<ProductosDto> result = productosService.readAll();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getProdnombre());
        assertEquals(1500.0, result.get(0).getProdprecio());
        assertTrue(result.get(0).getActivo());
        verify(productosRepository, times(1)).findAll();
    }
}
