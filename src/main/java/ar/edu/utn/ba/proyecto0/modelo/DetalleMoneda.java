package ar.edu.utn.ba.proyecto0.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleMoneda {
  private String nombre;
  private String simbolo;
}
