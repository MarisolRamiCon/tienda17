package com.example.tienda17.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedoresRequest {
    private String nombreEmpresa;
    private String contacto;
    private String email;
    private String telefono;
}
