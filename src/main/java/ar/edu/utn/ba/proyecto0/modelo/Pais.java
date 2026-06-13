package ar.edu.utn.ba.proyecto0.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "poblacion")
public class Pais {
  @NonNull
  private String nombre;
  @NonNull
  private String capital;
  private String region;
  private long poblacion;
  private Map<String, DetalleMoneda> monedas;
  private Map<String, String> idiomas;

  public String toLineString() {
    return this.nombre + " - " + this.capital + " - " + this.region + " - " + this.poblacion;
  }

  public static Pais crear(String nombre, String capital, String region, long poblacion, Map<String,DetalleMoneda> detalleMoneda, Map<String,String> idiomas) {
    Objects.requireNonNull(nombre, "nombre");
    if(capital == null) {
      throw new NullPointerException("La capital no puede ser null");
    }
    if(poblacion < 0) {
      throw new IllegalArgumentException("La población no puede ser negativa");
    }
    return new Pais(nombre, capital, region, poblacion, detalleMoneda, idiomas);
  }
}

/*
new Pais(
    "Argentina",
    "Buenos Aires",
    "Americas",
    45000000L,
    Map.of("ARS", new DetalleMoneda("Argentine peso", "$")),
    Map.of("spa", "Spanish")
)
* */
