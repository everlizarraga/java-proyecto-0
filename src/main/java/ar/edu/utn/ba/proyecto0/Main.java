package ar.edu.utn.ba.proyecto0;

import ar.edu.utn.ba.proyecto0.catalogo.CatalogoPaises;
import ar.edu.utn.ba.proyecto0.catalogo.PaisNoEncontradoException;
import ar.edu.utn.ba.proyecto0.catalogo.RegionDesconocidaException;
import ar.edu.utn.ba.proyecto0.modelo.DetalleMoneda;
import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    CatalogoPaises catalogo = new CatalogoPaises();
    System.out.println("Todos los idiomas: ");
    catalogo.todosLosIdiomas().forEach(p -> System.out.println("> " + p));
    System.out.println("Todos los idiomas 2:");
    catalogo.todosLosIdiomas2().forEach(p -> System.out.println(">> " + p));

    System.out.println("==================");
    System.out.println("Cantidad de idiomas unicos: " + catalogo.cantidadIdiomasUnicos());
    System.out.println("==================");
    System.out.println("Lista de símbolos de moneda  : " + catalogo.simbolosDeMoneda());
    System.out.println("Lista de símbolos de moneda 2: " + catalogo.simbolosDeMoneda2());
    System.out.println("==================");
    System.out.println("Pais con mas idiomas: " + catalogo.paisConMasIdiomas());
    System.out.println("==================");
    System.out.println("Hay pais plurilingue: " + catalogo.hayPaisPlurilingue());
    System.out.println("==================");
    System.out.println("Map<String, List<Pais>>: " + catalogo.paisesPorIdioma());
    catalogo.paisesPorIdioma().forEach((key, value) -> {
      System.out.println("> " + key + ": " + value.stream().map(Pais::getNombre).toList());
    });
    System.out.println("==================");
    System.out.println("Países concatenados: " + catalogo.nombresConcatenados());
    System.out.println("Todos los idiomas: " + catalogo.todosLosIdiomas2());
    System.out.println("Todos los idiomas: " + catalogo.todosLosIdiomas());

    System.out.println("==================");
    try {
      Pais argentina = catalogo.buscarPorNombreObligatorio("Argentina");
      System.out.println("OK: " + argentina);

      //Pais atlantis = catalogo.buscarPorNombreObligatorio("Atlantis");
      Pais atlantis = catalogo.buscarPorNombreObligatorio(null); // <-- Falla
      System.out.println("OK: " + atlantis);
    } catch (PaisNoEncontradoException e) {
      System.out.println("Error de búsqueda: " + e.getMessage());
      System.out.println("Buscábamos: " + e.getNombreBuscado());
    } catch (IllegalArgumentException e) {
      System.out.println("Argumento inválido: " + e.getMessage());
    } catch (Exception e) {
      //System.err.println("Error inesperado: " + e.getMessage());
      System.out.println("Error inesperado: " + e.getMessage());
      System.out.println("<<<<>>>>");
      //e.printStackTrace();
    } finally {
      System.out.println("Búsqueda completada");
    }

    System.out.println("==================");
    String region = "Atlantis";
    System.out.println("Buscar por region desconocida: " + region);
    try {
      var paisesPorRegionEspecifica = catalogo.buscarPorRegion(region);
      paisesPorRegionEspecifica.forEach(p -> System.out.println("> " + p.getNombre()));
    } catch (RegionDesconocidaException e) {
      System.out.println("ERROR:: " + e.getMessage());
      System.out.println("Regiones Disponibles: " + e.getRegionesDisponibles());
    }

    System.out.println("======== FIN =========");
  }

}
