package inndata172.ejercicioTarea.service;

import inndata172.ejercicioTarea.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface IProductosService {
    public List<Productos> ReadAll();
    public Optional<Productos> ReadById(Integer idproducto);
    public Productos create(Productos productos);
    public Productos update(Productos productos);
    public String deleteById(Integer idproducto);

}
