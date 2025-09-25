package inndata172.ejercicioTarea.repository;

import inndata172.ejercicioTarea.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductosRepository extends JpaRepository<Productos, Integer> {

}
