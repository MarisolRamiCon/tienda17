package com.example.tienda17.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "producto")
    private int producto;
    @Column(name = "stock")
    private int stock;
    @Column(name = "activo")
    private Boolean activo;

}
