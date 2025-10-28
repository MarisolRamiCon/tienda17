package com.example.tienda17.repository;


import com.example.tienda17.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmpleadoRepository extends JpaRepository <Empleado, Integer> {

    // Empleados inactivos (baja lógica)
    @Query(value = "SELECT * FROM empleado WHERE activo = false", nativeQuery = true)
    List<Empleado> empleadosInactivos();

    // Empleados activos
    @Query(value = "SELECT * FROM empleado WHERE activo = true", nativeQuery = true)
    List<Empleado> empleadosActivos();


    @Query(value = "SELECT * FROM empleado WHERE salario > ?1", nativeQuery = true)
    List<Empleado> empleadosConSalarioMayorA(double salario);


}
