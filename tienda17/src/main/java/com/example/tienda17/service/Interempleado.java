package com.example.tienda17.service;


import com.example.tienda17.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface Interempleado {
    public List<Empleado> readAll();
    Optional<Empleado> ReadById(Integer id);
    public Empleado create(Empleado empleado);
    public Empleado update(Empleado empleado);
    public String deleteById(Integer id);
    List<Empleado> findByActivoTrue();
    List<Empleado> findByActivoFalse();
    List<Empleado> findBySalarioGreaterThan(double salario);
}