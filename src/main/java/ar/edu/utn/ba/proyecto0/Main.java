package ar.edu.utn.ba.proyecto0;

import ar.edu.utn.ba.proyecto0.catalogo.CatalogoPaises;
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
  }

}
