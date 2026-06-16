package ar.edu.utn.ba.proyecto0.io;

import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class CargadorDePaisesDesdeCSV {

  public List<Pais> cargarDesde(Path archivo) throws IOException {
    try (Stream<String> lineas = Files.lines(archivo)) {
      return lineas
          .skip(1)                  // saltar el header
          .map(this::parsearLinea)
          .filter(Objects::nonNull)
          .toList();
    }
  }

  public List<String> cargarLineasCrudas(Path archivo) throws IOException {
    try (Stream<String> lineas = Files.lines(archivo)) {
      return lineas
          .skip(1)
          .toList();
    }
  }

  private Pais parsearLinea(String linea) {
    String[] campos = linea.split(",");
    if (campos.length < 4) {
      System.err.println("Línea inválida ignorada: " + linea);
      return null;
      //throw new FormatoCsvInvalidoException(campos.length);
    }
    return new Pais(
        campos[0],                    // nombre
        campos[1],                    // capital
        campos[2],                    // region
        Long.parseLong(campos[3]),    // poblacion
        Map.of(),                     // monedas (vacío por ahora)
        Map.of()                      // idiomas (vacío por ahora)
    );
  }

}
