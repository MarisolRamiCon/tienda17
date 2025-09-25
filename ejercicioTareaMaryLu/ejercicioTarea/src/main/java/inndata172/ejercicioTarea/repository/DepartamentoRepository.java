package inndata172.ejercicioTarea.repository;

import inndata172.ejercicioTarea.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Integer> {
    //Metodos personalizado
    //Listar los departamentos que su precio sea mayor que 60000
    public List<Departamento> findByPrecioGreaterThan(double precio);
    public List<Departamento> findByPrecioGreaterThanEqual(double precio);
    //Listar los departamentos que su precio sea mayor o igual que 60000
    //y los m2 sean mayores que 60
    public List<Departamento> findByM2GreaterThanAndPrecioGreaterThanEqual(String m2, Double precio);
    //metodo personalizado con query
    @Query(value = "select * from departamento where precio>=:precio and m2>:m2;", nativeQuery = true)
    public List<Departamento> m2AndPrecio(int m2, double precio);


}
