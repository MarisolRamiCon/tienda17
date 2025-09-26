package com.example.tienda17.service.impl;

import com.example.tienda17.feign.PedidosCliente;
import com.example.tienda17.model.Pedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidosServiceTest {

    @Mock
    private PedidosCliente pedidosCliente;

    @InjectMocks
    private PedidosService pedidosService;

    @Test
    void testReadAll() {
        Pedido p1 = new Pedido("Producto1", 100.0, true);
        Pedido p2 = new Pedido("Producto2", 50.0, false);
        Pedido p3 = new Pedido("Producto3", 75.0, true);

        when(pedidosCliente.readAll()).thenReturn(List.of(p1, p2, p3));

        List<Pedido> result = pedidosService.readAll();

        // Solo deben venir los activos
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(Pedido::getActivo));
        verify(pedidosCliente, times(1)).readAll();
    }

    @Test
    void testReadById() {
        Pedido p = new Pedido("Producto1", 100.0, true);

        when(pedidosCliente.readById("Producto1")).thenReturn(p);

        Pedido result = pedidosService.readById("Producto1");

        assertNotNull(result);
        assertEquals("Producto1", result.getProducto());
        assertEquals(100.0, result.getPrecio());
        verify(pedidosCliente, times(1)).readById("Producto1");
    }

    @Test
    void testCreate() {
        Pedido p = new Pedido("Producto1", 100.0, true);

        when(pedidosCliente.create(p)).thenReturn(p);

        Pedido result = pedidosService.create(p);

        assertNotNull(result);
        assertEquals("Producto1", result.getProducto());
        verify(pedidosCliente, times(1)).create(p);
    }

    @Test
    void testUpdate() {
        Pedido p = new Pedido("Producto1", 120.0, true);

        when(pedidosCliente.update("Producto1", p)).thenReturn(p);

        Pedido result = pedidosService.update("Producto1", p);

        assertNotNull(result);
        assertEquals(120.0, result.getPrecio());
        verify(pedidosCliente, times(1)).update("Producto1", p);
    }

    @Test
    void testDelete() {
        Pedido p = new Pedido("Producto1", 100.0, true);
        Pedido pInactivo = new Pedido("Producto1", 100.0, false);

        when(pedidosCliente.readById("Producto1")).thenReturn(p);
        when(pedidosCliente.update("Producto1", p)).thenReturn(pInactivo);

        Pedido result = pedidosService.delete("Producto1");

        assertNotNull(result);
        assertFalse(result.getActivo());
        verify(pedidosCliente, times(1)).readById("Producto1");
        verify(pedidosCliente, times(1)).update("Producto1", p);
    }
}
