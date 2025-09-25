package com.example.tienda17.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
    private String producto;
    private Double precio;
    private Boolean activo = true;
}
