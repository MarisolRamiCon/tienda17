package com.example.tienda17.controller;

import com.example.tienda17.model.Pedido;
import com.example.tienda17.service.impl.PedidosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PedidosControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private PedidosService pedidosService;

    private PedidosController pedidosController;

    private Pedido pedidoActivo;
    private Pedido pedidoInactivo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();

        pedidoActivo = new Pedido("Producto1", 100.0, true);
        pedidoInactivo = new Pedido("Producto1", 100.0, false);

        pedidosController = new PedidosController(pedidosService);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidosController).build();
    }

    @Test
    void getAll() throws Exception {
        when(pedidosService.readAll()).thenReturn(List.of(pedidoActivo));

        mockMvc.perform(get("/api/v1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].producto").value("Producto1"))
                .andExpect(jsonPath("$[0].precio").value(100.0))
                .andExpect(jsonPath("$[0].activo").value(true));

        verify(pedidosService, times(1)).readAll();
    }

    @Test
    void getById() throws Exception {
        when(pedidosService.readById("1")).thenReturn(pedidoActivo);

        mockMvc.perform(get("/api/v1/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto").value("Producto1"))
                .andExpect(jsonPath("$.precio").value(100.0))
                .andExpect(jsonPath("$.activo").value(true));

        verify(pedidosService, times(1)).readById("1");
    }

    @Test
    void create() throws Exception {
        when(pedidosService.create(any(Pedido.class))).thenReturn(pedidoActivo);

        mockMvc.perform(post("/api/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoActivo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto").value("Producto1"))
                .andExpect(jsonPath("$.activo").value(true));

        verify(pedidosService, times(1)).create(any(Pedido.class));
    }

    @Test
    void update() throws Exception {
        when(pedidosService.update(eq("1"), any(Pedido.class))).thenReturn(pedidoActivo);

        mockMvc.perform(put("/api/v1/pedidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoActivo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto").value("Producto1"))
                .andExpect(jsonPath("$.activo").value(true));

        verify(pedidosService, times(1)).update(eq("1"), any(Pedido.class));
    }

}
