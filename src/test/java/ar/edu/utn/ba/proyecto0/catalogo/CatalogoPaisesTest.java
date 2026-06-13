package ar.edu.utn.ba.proyecto0.catalogo;

import ar.edu.utn.ba.proyecto0.modelo.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogoPaisesTest {

  private CatalogoPaises catalogo;

  @BeforeEach
  void setUp() {
    // Arrange - Global
    catalogo = new CatalogoPaises();
  }

  @Test
  @DisplayName("El catálogo debe contener 6 países al iniciarse")
  void elCatalogoTieneSeisPaises() {
    // Arrange - preparar
    //CatalogoPaises catalogo = new CatalogoPaises();

    // Act - ejecutar
    //int cantidad = catalogo.cantidad();

    // Assert - verificar
    //assertEquals(esperado, obtenido)
    //assertEquals(6, cantidad);

    assertThat(catalogo.cantidad()).isEqualTo(6); // <- Podes encadenar
  }

  @Test
  void buscarArgentinaDevuelveResultadoPresente() {
    // Act
    Optional<Pais> resultado = catalogo.buscarPorNombre("Argentina");

    // Assert
    assertThat(resultado).isPresent();
    assertThat(resultado.get().getCapital()).isEqualTo("Buenos Aires");
  }

  @Test
  void buscarPaisesInexistentesDevuelveOptionalVacio() {
    // Act
    Optional<Pais> resultado = catalogo.buscarPorNombre("Atlantis");

    // Assert
    assertThat(resultado).isEmpty();
  }

  @Test
  void paisesDeAmericaContienenLosTresEsperados() {
    // Act
    List<Pais> americanos = catalogo.buscarPorRegion("Americas");

    // Assert
    assertThat(americanos)
        .hasSize(3)
        .extracting(Pais::getNombre)
        //.contains("Argentina", "Brasil", "Chile");                  // Al menos están estos, pueden haber más
        //.containsExactly("Argentina", "Brasil", "Chile");           // Están exactamente estos, en este orden
        //.containsExactly("Argentina", "Brasil", "Chile");           // Están exactamente estos, en este orden
        .containsExactlyInAnyOrder("Argentina", "Brasil", "Chile"); // Están exactamente estos, en cualquier orden
    //.containsOnly("Argentina", "Brasil", "Chile");        // Solo estos, sin importar duplicados
  }

  /*
  @Test
  void buscarPorRegionInexistenteDevuelveListaVacia() {
    // Act
    List<Pais> resultados = catalogo.buscarPorRegion("Antártica");

    // Assert
    assertThat(resultados).isEmpty();
  }
  */

  @Test
  @DisplayName("Buscar por region desconocida lanza RegionDesconocidaException")
  void buscarPorRegionInexistente_lanzaRegionDesconocidaException() {
    assertThatThrownBy(() -> catalogo.buscarPorRegion("Atlantis"))
        .isInstanceOf(RegionDesconocidaException.class);
  }

  @Test
  void elPaisMasPobladoEsBrasil() {
    // Act
    Optional<Pais> masPoblado = catalogo.paisMasPoblado();

    // Assert
    assertThat(masPoblado)
        .isPresent()
        .map(Pais::getNombre)
        .hasValue("Brasil");
  }

  @Test
  void hayDosPaisesUsandoEuro() {
    // Act
    List<Pais> pagaEuro = catalogo.buscarPorMoneda("EUR");

    // Assert
    assertThat(pagaEuro)
        .hasSize(2)
        .extracting(Pais::getNombre)
        //.map(Pais::getNombre)
        .containsExactlyInAnyOrder("España", "Francia");
  }

  // EJERCICIOS

  // Ejercicio 01
  @Test
  @DisplayName("El buscador por capital de Madrid devuelve Optional<España>")
  void buscarPorCapitalMadirdDevuelveOptionalDeEspania() {
    // Act
    Optional<Pais> resultado = catalogo.buscarPorCapital("Madrid");

    // Assert
    assertThat(resultado).isPresent();
    assertThat(resultado)
        .get()
        .extracting(Pais::getNombre)
        .isEqualTo("España");

    //assertThat(resultado.get().getNombre()).isEqualTo("España");

    assertThat(resultado)
        .map(Pais::getNombre)
        .hasValue("España");
  }

  // Ejercicio 02
  @Test
  @DisplayName("Los nombres de los paises del catalogo debe ser exacta")
  void obtenerNombresConcatenadosDePaises() { // "Argentina, Brasil, Chile, España, Francia, Japón"
    // Act
    String str = catalogo.nombresConcatenados();

    // Assert
    assertThat(str).isEqualTo("Argentina, Brasil, Chile, España, Francia, Japón");
  }

  // Ejercicio 03
  @Test
  @DisplayName("Obtener conteo de regiones correcta")
  void obtenerConteoDeRegionesCorrecta() {
    // Act
    Map<String, Long> conteo = catalogo.contarPorRegion();

    // Assert
    assertThat(conteo)
        .containsEntry("Americas", 3L)
        .containsEntry("Europe", 2L)
        .containsEntry("Asia", 1L);
  }

  // Ejercicio 04
  @Test
  @DisplayName("El catalogo debe contener exactamente 4 idiomas: Spanish, Portuguese, French, Japanese")
  void verificandoIdiomasEsperados() {
    // Act
    List<String> idiomas = catalogo.todosLosIdiomas2();

    // Assert
    assertThat(idiomas)
        .hasSize(4)
        .containsExactlyInAnyOrder("Spanish", "Portuguese", "French", "Japanese");
  }

  @ParameterizedTest
  @ValueSource(strings = {"Argentina", "Brasil", "Chile", "España"})
  void todosEstosPaisesExistenEnElCatalogo(String nombre) {
    assertThat(catalogo.buscarPorNombre(nombre)).isPresent();
  }

  // ETAPA 8 - TESTEAR EXCEPCIONES

  /*
  assertThatThrownBy(lambda) — ejecuta la lambda y verifica que tire excepción.
  .isInstanceOf(Clase.class) — verifica el tipo.
  .hasMessage("...") — mensaje exacto.
  .hasMessageContaining("...") — el mensaje contiene esto.
  .hasMessageStartingWith("...") — empieza con esto.
  * */

  @Test
  void buscarPorNombreObligatorio_paisInexistente_lanzaExcepcion() {
    assertThatThrownBy(() -> catalogo.buscarPorNombreObligatorio("Atlantis"))
        .isInstanceOf(PaisNoEncontradoException.class)
        .hasMessageContaining("Atlantis");
  }

  @Test
  void buscarPorNombreObligatorio_null_lanzaIllegalArgument() {
    assertThatThrownBy(() -> catalogo.buscarPorNombreObligatorio(null))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void buscarPorNombreObligatorio_vacio_lanzaIllegalArgument() {
    assertThatThrownBy(() -> catalogo.buscarPorNombreObligatorio(""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("vacío");
  }


}
