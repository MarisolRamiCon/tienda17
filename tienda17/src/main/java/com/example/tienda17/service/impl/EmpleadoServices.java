package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Empleado;
import com.example.tienda17.repository.EmpleadoRepository;
import com.example.tienda17.service.Interempleado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmpleadoServices implements Interempleado {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServices(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> readAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> ReadById(Integer id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado create(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado update(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }



    @Override
    public String deleteById(Integer id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empleado con id " + id + " no encontrado"));

        empleado.setActivo(false); // baja lógica
        empleadoRepository.save(empleado);
        return "Empleado con id " + id + " dado de baja correctamente";
    }


    // ✅ Empleados activos
    @Override
    public List<Empleado> findByActivoTrue() {
        return empleadoRepository.empleadosActivos();
    }

    // ✅ Empleados inactivos
    @Override
    public List<Empleado> findByActivoFalse() {
        return empleadoRepository.empleadosInactivos();
    }

    // ✅ Empleados con salario mayor
    @Override
    public List<Empleado> findBySalarioGreaterThan(double salario) {
        return empleadoRepository.empleadosConSalarioMayorA(salario);
    }
}
