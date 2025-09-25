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
@Table(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer idproducto;
    @Column(name = "prodnombre")
    private String prodnombre;
    @Column(name = "proddescripcion")
    private String proddescripcion;
    @Column(name = "prodprecio")
    private double prodprecio;
    @Column(name = "prodcategoria")
    private String prodcategoria;
    @Column(name = "idproveedor")
    private Integer idproveedor;
    @Column(name = "prodstock")
    private Integer prodstock;

}
