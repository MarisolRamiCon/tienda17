package com.example.tienda17.controller;

import com.example.tienda17.dto.ProveedoresRequest;
import com.example.tienda17.dto.ProveedoresResponse;
import com.example.tienda17.exception.GlobalExceptionHandler;
import com.example.tienda17.service.impl.ProveedoresService;
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

class ProveedoresControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProveedoresService proveedoresService;

    @InjectMocks
    private ProveedoresController proveedoresController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(proveedoresController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Si tienes GlobalExceptionHandler
                .build();
    }

    @Test
    void readAll() throws Exception {
        ProveedoresResponse p1 = new ProveedoresResponse("Empresa1", "Juan", "123");
        ProveedoresResponse p2 = new ProveedoresResponse("Empresa2", "Ana", "456");

        when(proveedoresService.readAll()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/inndata17/tienda/proveedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreEmpresa").value("Empresa1"))
                .andExpect(jsonPath("$[1].contacto").value("Ana"));
    }

    @Test
    void readByIdSuccess() throws Exception {
        ProveedoresResponse proveedor = new ProveedoresResponse("Empresa1", "Juan", "123");
        when(proveedoresService.readById(1)).thenReturn(Optional.of(proveedor));

        mockMvc.perform(get("/inndata17/tienda/proveedores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEmpresa").value("Empresa1"))
                .andExpect(jsonPath("$.contacto").value("Juan"));
    }

    @Test
    void readByIdNotFound() throws Exception {
        when(proveedoresService.readById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/inndata17/tienda/proveedores/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Proveedor no encontrado con id: 99"));
    }

    @Test
    void create() throws Exception {
        ProveedoresResponse response = new ProveedoresResponse("Empresa1", "Juan", "123");
        when(proveedoresService.create(any(ProveedoresRequest.class))).thenReturn(response);

        String json = """
                {"nombreEmpresa":"Empresa1","contacto":"Juan","telefono":"123"}
                """;

        mockMvc.perform(post("/inndata17/tienda/proveedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEmpresa").value("Empresa1"))
                .andExpect(jsonPath("$.contacto").value("Juan"));
    }

    @Test
    void update() throws Exception {
        ProveedoresResponse response = new ProveedoresResponse("Empresa1", "Juan", "123");
        when(proveedoresService.update(eq(1), any(ProveedoresRequest.class))).thenReturn(response);

        String json = """
                {"nombreEmpresa":"Empresa1","contacto":"Juan","telefono":"123"}
                """;

        mockMvc.perform(put("/inndata17/tienda/proveedores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEmpresa").value("Empresa1"))
                .andExpect(jsonPath("$.contacto").value("Juan"));
    }

}
