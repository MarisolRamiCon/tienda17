package com.example.tienda17.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
    @NoArgsConstructor
    @AllArgsConstructor

    public class ContratoRequest {

    private String nombre;
    private String apellido;
    private String puesto;
    private boolean Activo;


    }


