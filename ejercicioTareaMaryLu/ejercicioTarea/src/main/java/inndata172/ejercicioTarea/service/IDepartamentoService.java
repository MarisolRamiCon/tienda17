package inndata172.ejercicioTarea.service;
import inndata172.ejercicioTarea.entity.Departamento;
import java.util.List;
import java.util.Optional;


public interface IDepartamentoService {
    public List<Departamento> ReadAll();
    public Optional<Departamento> ReadById(Integer id);
    public Departamento create(Departamento departamento);
    public Departamento update(Departamento departamento);
    public String deleteById(Integer id);
    public List<Departamento> precioMayor(Double precio);
    public List<Departamento> precioM2(String m2, Double precio);
}
