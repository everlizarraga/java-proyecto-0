package ar.edu.utn.ba.proyecto0;

import ar.edu.utn.ba.proyecto0.catalogo.CatalogoPaises;
import ar.edu.utn.ba.proyecto0.modelo.DetalleMoneda;
import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        CatalogoPaises catalogo = new CatalogoPaises();

        System.out.println("\n=== Países que usan EUR ===");
        catalogo.buscarPorMoneda("EUR")
                .forEach(p -> System.out.println("  - " + p.getNombre()));

        System.out.println("\n=== Países que usan ARS ===");
        catalogo.buscarPorMoneda("ARS")
                .forEach(p -> System.out.println("  - " + p.getNombre()));

        System.out.println("===============");
        Map<String, DetalleMoneda> monedas = catalogo.buscarPorNombre("Argentina")
                .map(Pais::getMonedas)
                .orElse(Map.of());

        monedas.forEach((codigo, detalle) ->
                System.out.println(codigo + " → " + detalle.getNombre() + " (" + detalle.getSimbolo() + ")")
        );

        System.out.println("=====================");
        // Solo claves:
        for (String codigo : monedas.keySet()) {
            System.out.println("Código: " + codigo);
        }

        // Solo valores:
        for (DetalleMoneda detalle : monedas.values()) {
            System.out.println("Moneda: " + detalle.getNombre());
        }

        System.out.println("=====================");
        monedas.entrySet().stream()
                .filter(entry -> entry.getValue().getSimbolo().equals("$"))
                .forEach(entry ->
                        System.out.println(entry.getKey() + " usa el símbolo $ -- " + entry.getValue())
                );

        System.out.println("=====================");
        System.out.println("\n=== Idiomas únicos en el catálogo ===");
        catalogo.todosLosIdiomas()
                .forEach(System.out::println);


        System.out.println("=====================");
        System.out.println("\n=== Países por moneda ===");
        catalogo.paisesAgrupadosPorMonedaPrincipal()
                .forEach((moneda, lista) -> {
                    System.out.println(moneda + ":");
                    lista.forEach(p -> System.out.println("  - " + p.getNombre()));
                });

        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");

    }

}
