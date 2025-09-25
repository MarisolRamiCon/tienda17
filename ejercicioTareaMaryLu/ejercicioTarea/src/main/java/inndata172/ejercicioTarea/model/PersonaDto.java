package inndata172.ejercicioTarea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PersonaDto {
    private String pnombre;
    private Integer edad;
    private Integer iddepartamento;

}
