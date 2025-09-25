package inndata172.ejercicioTarea.service.impl;

import inndata172.ejercicioTarea.entity.Departamento;
import inndata172.ejercicioTarea.feign.ViajeCliente;
import inndata172.ejercicioTarea.model.Viajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service

public class ViajeService implements ViajeCliente{
    @Autowired
 ViajeCliente viajeCliente;
    @Override
    public List<Viajes> ReadAll() {
        return viajeCliente.ReadAll();
    }

    @Override
    public Viajes ReadById(Integer id) {
        return viajeCliente.ReadById(id);
    }

    @Override
    public Viajes create(Viajes viajes) {
        return viajeCliente.create(viajes);
    }

    @Override
    public Viajes update(int id, Viajes viajes) {
        return viajeCliente.update(id, viajes);
    }

    @Override
    public void delete(int id) {
        viajeCliente.delete(id);
    }


}
