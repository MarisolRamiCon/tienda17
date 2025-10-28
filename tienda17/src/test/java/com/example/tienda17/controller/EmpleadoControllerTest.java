package com.example.tienda17.controller;

import com.example.tienda17.entity.Empleado;
import com.example.tienda17.service.impl.EmpleadoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoServices empleadoServices;

    @Autowired
    private ObjectMapper objectMapper;

    private Empleado empleado1;
    private Empleado empleado2;

    @BeforeEach
    void setUp() {
        empleado1 = new Empleado();
        empleado1.setId(1);
        empleado1.setNombre("Juan");
        empleado1.setApellidos("Pérez");
        empleado1.setPuesto("Cajero");
        empleado1.setSalario(2500.50);
        empleado1.setFecha(null);
        empleado1.setActivo(true);

        empleado2 = new Empleado();
        empleado2.setId(2);
        empleado2.setNombre("Ana");
        empleado2.setApellidos("López");
        empleado2.setPuesto("Gerente");
        empleado2.setSalario(5000.00);
        empleado2.setFecha(null);
        empleado2.setActivo(false);
    }

    @Test
    void testReadAll() throws Exception {
        List<Empleado> empleados = Arrays.asList(empleado1, empleado2);
        when(empleadoServices.readAll()).thenReturn(empleados);

        mockMvc.perform(get("/inndata17/tienda/empleado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].nombre", is("Juan")))
                .andExpect(jsonPath("$[1].nombre", is("Ana")));
    }

    @Test
    void testReadById() throws Exception {
        when(empleadoServices.ReadById(1)).thenReturn(Optional.of(empleado1));

        mockMvc.perform(get("/inndata17/tienda/empleado/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Juan")))
                .andExpect(jsonPath("$.puesto", is("Cajero")));
    }

    @Test
    void testCreateEmpleado() throws Exception {
        when(empleadoServices.create(any(Empleado.class))).thenReturn(empleado1);

        mockMvc.perform(post("/inndata17/tienda/empleado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Juan")))
                .andExpect(jsonPath("$.salario", is(2500.50)));
    }

    @Test
    void testUpdateEmpleado() throws Exception {
        when(empleadoServices.update(any(Empleado.class))).thenReturn(empleado1);

        mockMvc.perform(put("/inndata17/tienda/empleado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Juan")));
    }

    @Test
    void testDeleteEmpleado() throws Exception {
        when(empleadoServices.deleteById(1)).thenReturn("Empleado eliminado correctamente");

        mockMvc.perform(delete("/inndata17/tienda/empleado/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Empleado eliminado correctamente"));
    }

    @Test
    void testEmpleadosActivos() throws Exception {
        when(empleadoServices.findByActivoTrue()).thenReturn(List.of(empleado1));

        mockMvc.perform(get("/inndata17/tienda/empleado/activos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].activo", is(true)));
    }

    @Test
    void testEmpleadosInactivos() throws Exception {
        when(empleadoServices.findByActivoFalse()).thenReturn(List.of(empleado2));

        mockMvc.perform(get("/inndata17/tienda/empleado/inactivos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].activo", is(false)));
    }

    @Test
    void testEmpleadosConSalarioMayorA() throws Exception {
        when(empleadoServices.findBySalarioGreaterThan(3000.00)).thenReturn(List.of(empleado2));

        mockMvc.perform(get("/inndata17/tienda/empleado/salario/3000.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Ana")))
                .andExpect(jsonPath("$[0].salario", is(5000.00)));
    }
}
