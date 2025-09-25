package inndata172.ejercicioTarea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Sustituye los controladores, getter y setter
@AllArgsConstructor //constructor con todos los argumentos
@NoArgsConstructor // constructor sin argumentos
@Data //getter y setter

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    //*@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona")
    private Integer idpersona;
    @Column(name = "pnombre")
    private String pnombre;
    @Column(name = "pdireccion")
    private String pdireccion;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "activo")
    private boolean activo = true;
    /*@Column(name = "departamento")
    private Integer departamento;*/
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departamento")
    private Departamento departamento;


}
