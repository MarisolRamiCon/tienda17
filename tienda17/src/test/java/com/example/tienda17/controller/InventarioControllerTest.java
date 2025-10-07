package com.example.tienda17.controller;

import com.example.tienda17.entity.Inventario;
import com.example.tienda17.exception.GlobalExceptionHandler;
import com.example.tienda17.service.impl.InventarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InventarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InventarioService inventarioService;

    @InjectMocks
    private InventarioController inventarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventarioController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Si tienes uno
                .build();
    }

    @Test
    void readAll() throws Exception {
        Inventario p1 = new Inventario(1, 101, 10);
        Inventario p2 = new Inventario(2, 102, 5);
        when(inventarioService.readAll()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/inndata17/tienda/inventario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].producto").value(101))
                .andExpect(jsonPath("$[1].stock").value(5));
    }

    @Test
    void readByIdSuccess() throws Exception {
        Inventario producto = new Inventario(1, 101, 10);
        when(inventarioService.ReadById(1)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/inndata17/tienda/inventario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto").value(101))
                .andExpect(jsonPath("$.stock").value(10));
    }

    @Test
    void readByIdNotFound() throws Exception {
        when(inventarioService.ReadById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/inndata17/tienda/inventario/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Inventario no encontrado con id: 99"));
    }

    @Test
    void create() throws Exception {
        Inventario producto = new Inventario(1, 101, 10);
        when(inventarioService.create(any(Inventario.class))).thenReturn(producto);

        String json = """
                {"id":1,"producto":101,"stock":10}
                """;

        mockMvc.perform(post("/inndata17/tienda/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto").value(101));
    }

    @Test
    void update() throws Exception {
        Inventario producto = new Inventario(1, 101, 15);
        when(inventarioService.update(any(Inventario.class))).thenReturn(producto);

        String json = """
                {"id":1,"producto":101,"stock":15}
                """;

        mockMvc.perform(put("/inndata17/tienda/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock").value(15));
    }


    @Test
    void stockMayor() throws Exception {
        Inventario p1 = new Inventario(1, 101, 20);
        Inventario p2 = new Inventario(2, 102, 15);

        when(inventarioService.stockMayor(10)).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/inndata17/tienda/inventarioMayor?stock=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stock").value(20))
                .andExpect(jsonPath("$[1].stock").value(15));
    }

    @Test
    void productosAgotados() throws Exception {
        Inventario producto = new Inventario(3, 103, 0);
        when(inventarioService.productosAgotados()).thenReturn(List.of(producto));

        mockMvc.perform(get("/inndata17/tienda/productosAgotados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stock").value(0));
    }
}
