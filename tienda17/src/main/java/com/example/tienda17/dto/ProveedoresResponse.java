package com.example.tienda17.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedoresResponse {
    private String nombreEmpresa;
    private String contacto;
    private String telefono;
}
