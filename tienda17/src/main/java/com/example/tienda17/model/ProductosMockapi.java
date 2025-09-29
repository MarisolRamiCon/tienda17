package com.example.tienda17.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductosMockapi {
    private Integer id;
    private String prodNombre;
    private Integer prodPrecio;
    private boolean prodActivo = true;

}
