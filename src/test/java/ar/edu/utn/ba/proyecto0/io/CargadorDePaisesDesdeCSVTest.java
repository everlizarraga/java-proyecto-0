package ar.edu.utn.ba.proyecto0.io;

import ar.edu.utn.ba.proyecto0.modelo.Pais;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CargadorDePaisesDesdeCSVTest {

  @Test
  void cargar_devuelveLaCantidadCorrectaDeLineas() throws IOException {
    // Arrange
    Path csv = crearCsvDePrueba();
    CargadorDePaisesDesdeCSV cargador = new CargadorDePaisesDesdeCSV();

    // Act
    List<Pais> paises = cargador.cargarDesde(csv);

    // Assert
    assertThat(paises).hasSize(2);
  }

  @Test
  void cargar_parseaLosCamposCorrectamente() throws IOException {
    // Arrange
    Path csv = crearCsvDePrueba();
    CargadorDePaisesDesdeCSV cargador = new CargadorDePaisesDesdeCSV();

    // Act
    List<Pais> paises = cargador.cargarDesde(csv);

    // Assert
    assertThat(paises)
        .extracting(Pais::getNombre)
        .containsExactly("Argentina", "Brasil");

    assertThat(paises.get(0).getPoblacion()).isEqualTo(45000000L);
  }

  @Test
  void cargar_archivoInexistente_lanzaIOException() {
    // Arrange
    Path inexistente = Path.of("no-existe.csv");
    CargadorDePaisesDesdeCSV cargador = new CargadorDePaisesDesdeCSV();

    // Act + Assert
    assertThatThrownBy(() -> cargador.cargarDesde(inexistente))
        .isInstanceOf(IOException.class);
  }

  // Helper: crea un CSV temporal para los tests
  private Path crearCsvDePrueba() throws IOException {
    Path tempFile = Files.createTempFile("paises-test", ".csv");
    List<String> lineas = List.of(
        "nombre,capital,region,poblacion",
        "Argentina,Buenos Aires,Americas,45000000",
        "Brasil,Brasilia,Americas,210000000"
    );
    Files.write(tempFile, lineas);
    return tempFile;
  }

}
