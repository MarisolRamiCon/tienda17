package com.example.tienda17.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aspirantes {
         private String nombre;
         private String apeido;
         private  Boolean Activo;
         private String FechaContratacion;
         private Boolean Aceptado;
         private Integer id;
    }

