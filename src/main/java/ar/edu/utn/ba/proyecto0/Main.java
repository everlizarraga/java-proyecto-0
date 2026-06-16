package ar.edu.utn.ba.proyecto0;

import ar.edu.utn.ba.proyecto0.catalogo.CatalogoPaises;
import ar.edu.utn.ba.proyecto0.catalogo.PaisNoEncontradoException;
import ar.edu.utn.ba.proyecto0.catalogo.RegionDesconocidaException;
import ar.edu.utn.ba.proyecto0.io.FormasDeLeerUnArchivoCSV;
import ar.edu.utn.ba.proyecto0.modelo.DetalleMoneda;
import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    CatalogoPaises catalogo = new CatalogoPaises();
    String pathPaisesCSV = "src/main/resources/paises.csv";
    System.out.println("PATH: " + pathPaisesCSV);

    /*
    try {
      FormasDeLeerUnArchivoCSV.probarFormas(pathPaisesCSV);
    } catch (IOException e) {
      System.out.println("Algo fallo en IO: " + e.getMessage());
      //throw new RuntimeException(e);
    }
     */

    try {
      Path archivo = Path.of(pathPaisesCSV);
      CatalogoPaises catalogoCSV = new CatalogoPaises(archivo);

      System.out.println("Cargué " + catalogo.cantidad() + " países desde el CSV");
      catalogoCSV.getTodos()
          .forEach(p -> System.out.println(" - " + p.getNombre()));

      System.out.println("\n=== Países de Americas ===");
      catalogoCSV.buscarPorRegion("Americas")
          .forEach(p -> System.out.println(" - " + p.getNombre()));
    } catch (IOException e) {
      //throw new RuntimeException(e);
      System.err.println("Error leyendo el archivo: " + e.getMessage());
    }

  }
}
