package ar.edu.utn.ba.proyecto0;

import ar.edu.utn.ba.proyecto0.catalogo.CatalogoPaises;
import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        CatalogoPaises catalogo = new CatalogoPaises();

        // === Búsquedas con streams ===
        System.out.println("=== Buscar Argentina ===");
        catalogo.buscarPorNombre("Argentina")
                .ifPresent(System.out::println);

        // === País más poblado ===
        System.out.println("\n=== Más poblado ===");
        catalogo.paisMasPoblado()
                .ifPresent(p -> System.out.println(p.getNombre() + ": " + p.getPoblacion()));

        // === Población total ===
        System.out.println("\n=== Población total ===");
        System.out.println("Total: " + catalogo.poblacionTotal());

        // === Nombres concatenados ===
        System.out.println("\n=== Nombres ===");
        System.out.println(catalogo.nombresConcatenados());

        // === Conteo por región ===
        System.out.println("\n=== Por región ===");
        Map<String, Long> conteo = catalogo.contarPorRegion();
        conteo.forEach((region, cantidad) ->
                System.out.println(region + ": " + cantidad)
        );

        // === Ordenado alfabéticamente ===
        System.out.println("\n=== Alfabético ===");
        catalogo.paisesOrdenadosAlfabeticamente()
                .forEach(p -> System.out.println("  - " + p.getNombre()));

        // === Stream inline en Main (sin método del catálogo) ===
        System.out.println("\n=== Países con más de 50M de habitantes ===");
        catalogo.getTodos().stream()
                .filter(p -> p.getPoblacion() > 50_000_000L)
                .map(Pais::getNombre)
                .forEach(n -> System.out.println("  - " + n));
    }

}
