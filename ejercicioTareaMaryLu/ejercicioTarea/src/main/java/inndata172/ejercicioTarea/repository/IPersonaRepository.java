package inndata172.ejercicioTarea.repository;

import inndata172.ejercicioTarea.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByActivoTrue();

    //Listar personas mayores a 30 años de edad
    public List<Persona> findByEdadGreaterThan(int edad);

    //metodo personalizado con query mayor edad 40
    @Query(value = "select * from persona where edad>=:edad", nativeQuery = true)
    public List<Persona> edadMayorquery(int edad);
}
