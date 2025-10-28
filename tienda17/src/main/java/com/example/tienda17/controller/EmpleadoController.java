package com.example.tienda17.controller;

import com.example.tienda17.entity.Empleado;
import com.example.tienda17.service.impl.EmpleadoServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@RestController
@RequestMapping("/inndata17/tienda")
public class EmpleadoController {


    private final EmpleadoServices empleadoServices;

    public EmpleadoController(EmpleadoServices empleadoServices) {
        this.empleadoServices = empleadoServices;
    }


    @GetMapping("/empleado")
    public List<Empleado> readAll() {
        return empleadoServices.readAll();
    }

    @GetMapping("/empleado/{id}")
    public Empleado readById(@PathVariable Integer id) {
        return empleadoServices.ReadById(id)
                .orElseThrow(() -> new NoSuchElementException("Inventario no encontrado con id: " + id));
    }


    @PostMapping("/empleado")
    public Empleado create(@RequestBody Empleado empleado) {
        return empleadoServices.create(empleado);
    }


    @PutMapping("/empleado")
    public Empleado update(@RequestBody Empleado empleado) {
        return empleadoServices.update(empleado);
    }
    @DeleteMapping("/empleado/{id}")
    public String delete(@PathVariable Integer id){
        return empleadoServices.deleteById(id);
    }


    @GetMapping("/empleado/activos")
    public List<Empleado> empleadosActivos() {
        return empleadoServices.findByActivoTrue();
    }

    @GetMapping("/empleado/inactivos")
    public List<Empleado> empleadosInactivos() {
        return empleadoServices.findByActivoFalse();
    }

    @GetMapping("/empleado/salario/{salario}")
    public List<Empleado> empleadosConSalarioMayorA(@PathVariable double salario) {
        return empleadoServices.findBySalarioGreaterThan(salario);
    }


}

