package com.example.tienda17.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductosDto {
    private Integer idproducto;
    private String prodnombre;
    private double prodprecio;
    private Boolean activo = true;
}
