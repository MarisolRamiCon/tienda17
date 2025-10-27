package com.example.tienda17.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Empleados")

public class Empleado {
    @Id
    @Column (name = "idempleado")
    private int id;
    @Column (name = "nombre")
    private String Nombre;
    @Column (name ="apeido")
    private String Apellidos;
    @Column (name ="puesto")
    private String Puesto;
    @Column (name = "Salario")
    private double salario;
    @Column (name ="Fecha")
    private Date fecha;
    @Column (name ="Activo")
    private boolean activo;


}
