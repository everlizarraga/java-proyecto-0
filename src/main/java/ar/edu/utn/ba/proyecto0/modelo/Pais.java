package ar.edu.utn.ba.proyecto0.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.Map;

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

/*
*
Map<String, String> idiomas = new HashMap<>();
idiomas.put("spa", "Spanish");                 // agregar/actualizar
idiomas.put("eng", "English");

String nombre = idiomas.get("spa");             // recuperar (devuelve null si no existe)
boolean hay = idiomas.containsKey("fra");       // chequear si existe
int cantidad = idiomas.size();                  // cuántas entradas

// Iterar:
idiomas.forEach((codigo, nombreIdioma) ->
    System.out.println(codigo + " → " + nombreIdioma)
);
*
* */