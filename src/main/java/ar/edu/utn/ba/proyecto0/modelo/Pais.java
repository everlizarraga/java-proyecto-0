package ar.edu.utn.ba.proyecto0.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "poblacion")
public class Pais {
    private String nombre;
    private String capital;
    private String region;
    private long poblacion;
}
