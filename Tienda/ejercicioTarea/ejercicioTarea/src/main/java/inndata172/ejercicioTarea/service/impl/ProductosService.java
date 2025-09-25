package inndata172.ejercicioTarea.service.impl;

import inndata172.ejercicioTarea.entity.Productos;
import inndata172.ejercicioTarea.repository.IProductosRepository;
import inndata172.ejercicioTarea.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductosService implements IProductosService {
    @Autowired
    IProductosRepository productosrepository;
    @Override
    public List<Productos> ReadAll() {
        return productosrepository.findAll();
    }
    @Override
    public Optional<Productos> ReadById(Integer idproducto) {
        return productosrepository.findById(idproducto);
    }
    @Override
    public Productos create(Productos productos) {
        return productosrepository.save(productos);}
    @Override
    public Productos update(Productos productos) {
        return productosrepository.save(productos);
    }
    @Override
    public String deleteById(Integer idproducto) {
        productosrepository.deleteById(idproducto);
        return "Registro eliminado: " + idproducto;
    }

}
