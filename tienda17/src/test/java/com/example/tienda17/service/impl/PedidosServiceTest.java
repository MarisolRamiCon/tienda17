package com.example.tienda17.service.impl;

import com.example.tienda17.feign.PedidosCliente;
import com.example.tienda17.model.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidosServiceTest {

    @Mock
    private PedidosCliente pedidosCliente;

    @InjectMocks
    private PedidosService pedidosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadAll() {
        Pedido p1 = new Pedido("Producto1", 100.0, true);
        Pedido p2 = new Pedido("Producto2", 50.0, false);

        when(pedidosCliente.readAll()).thenReturn(List.of(p1, p2));

        List<Pedido> result = pedidosService.readAll();

        assertEquals(1, result.size()); // Solo activo
        assertTrue(result.get(0).getActivo());
        verify(pedidosCliente, times(1)).readAll();
    }

    @Test
    void testReadById() {
        Pedido p = new Pedido("Producto1", 100.0, true);

        when(pedidosCliente.readById("1")).thenReturn(p);

        Pedido result = pedidosService.readById("1");

        assertNotNull(result);
        assertEquals("Producto1", result.getProducto());
        assertEquals(100.0, result.getPrecio());
        verify(pedidosCliente, times(1)).readById("1");
    }

    @Test
    void testCreate() {
        Pedido p = new Pedido("Producto1", 100.0, true);

        when(pedidosCliente.create(p)).thenReturn(p);

        Pedido result = pedidosService.create(p);

        assertNotNull(result);
        assertEquals("Producto1", result.getProducto());
        assertEquals(100.0, result.getPrecio());
        verify(pedidosCliente, times(1)).create(p);
    }

    @Test
    void testUpdate() {
        Pedido p = new Pedido("Producto1", 100.0, true);

        when(pedidosCliente.update("1", p)).thenReturn(p);

        Pedido result = pedidosService.update("1", p);

        assertNotNull(result);
        assertEquals("Producto1", result.getProducto());
        verify(pedidosCliente, times(1)).update("1", p);
    }

    @Test
    void testDelete() {
        Pedido p = new Pedido("Producto1", 100.0, true);

        when(pedidosCliente.readById("1")).thenReturn(p);
        when(pedidosCliente.update("1", p)).thenReturn(p);

        Pedido result = pedidosService.delete("1");

        assertNotNull(result);
        assertFalse(result.getActivo()); // Debe estar inactivo después del delete
        verify(pedidosCliente, times(1)).readById("1");
        verify(pedidosCliente, times(1)).update("1", p);
    }
}
