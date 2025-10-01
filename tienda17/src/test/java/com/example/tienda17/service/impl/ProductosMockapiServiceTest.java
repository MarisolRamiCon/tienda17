package com.example.tienda17.service.impl;

import com.example.tienda17.feign.IProductosMockapi;
import com.example.tienda17.model.ProductosMockapi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductosMockapiServiceTest {

    @Mock
    private IProductosMockapi productosMockapiClient;

    @InjectMocks
    private ProductosMockapiService productosMockapiService;

    private ProductosMockapi productoActivo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productoActivo = new ProductosMockapi();
        productoActivo.setId(1);
        productoActivo.setProdNombre("Teclado");
        productoActivo.setProdPrecio(250); // Cambiado a Integer
        productoActivo.setProdActivo(true);
    }

    @Test
    void testReadAll() {
        when(productosMockapiClient.ReadAll()).thenReturn(List.of(productoActivo));

        List<ProductosMockapi> result = productosMockapiService.ReadAll();

        assertEquals(1, result.size());
        assertEquals("Teclado", result.get(0).getProdNombre());
        verify(productosMockapiClient, times(1)).ReadAll();
    }

    @Test
    void testReadById() {
        when(productosMockapiClient.ReadById(1)).thenReturn(productoActivo);

        ProductosMockapi result = productosMockapiService.ReadById(1);

        assertNotNull(result);
        assertEquals("Teclado", result.getProdNombre());
        verify(productosMockapiClient, times(1)).ReadById(1);
    }

    @Test
    void testCreate() {
        when(productosMockapiClient.create(productoActivo)).thenReturn(productoActivo);

        ProductosMockapi result = productosMockapiService.create(productoActivo);

        assertNotNull(result);
        assertEquals("Teclado", result.getProdNombre());
        verify(productosMockapiClient, times(1)).create(productoActivo);
    }

    @Test
    void testUpdate() {
        when(productosMockapiClient.update(1, productoActivo)).thenReturn(productoActivo);

        ProductosMockapi result = productosMockapiService.update(1, productoActivo);

        assertNotNull(result);
        assertEquals("Teclado", result.getProdNombre());
        verify(productosMockapiClient, times(1)).update(1, productoActivo);
    }

    @Test
    void testDeleteLogicoFound() {
        when(productosMockapiClient.ReadById(1)).thenReturn(productoActivo);
        when(productosMockapiClient.update(1, productoActivo)).thenReturn(productoActivo);

        String result = productosMockapiService.deleteLogico(1);

        assertEquals("Producto borrado con el ID: 1", result);
        assertFalse(productoActivo.isProdActivo()); // Cambiado a isProdActivo()
        verify(productosMockapiClient, times(1)).ReadById(1);
        verify(productosMockapiClient, times(1)).update(1, productoActivo);
    }

    @Test
    void testDeleteLogicoNotFound() {
        when(productosMockapiClient.ReadById(99)).thenReturn(null);

        String result = productosMockapiService.deleteLogico(99);

        assertEquals("Producto no encontrado con el ID: 99", result);
        verify(productosMockapiClient, times(1)).ReadById(99);
        verify(productosMockapiClient, never()).update(eq(99), any(ProductosMockapi.class));
    }
}
