package inndata172.ejercicioTarea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor // constructor sin argumentos
@Data //getter y setter

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddepartamento")
    private Integer iddepartamento;
    @Column(name= "dnombre")
    private String dnombre;
    @Column(name = "m2")
    private String m2;
    @Column(name = "precio")
    private double precio;
    @Column(name = "eliminarlogico")
    private Boolean eliminarlogico=true;


}
