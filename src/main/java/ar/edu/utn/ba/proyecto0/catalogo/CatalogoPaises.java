package ar.edu.utn.ba.proyecto0.catalogo;

import ar.edu.utn.ba.proyecto0.modelo.DetalleMoneda;
import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogoPaises {
    private List<Pais> paises;

    public CatalogoPaises() {
        this.paises = new ArrayList<>();

        this.paises.add(new Pais(
                "Argentina",
                "Buenos Aires",
                "Americas",
                45000000L,
                Map.of("ARS", new DetalleMoneda("Argentine peso", "$")),
                Map.of("spa", "Spanish")
        ));

        this.paises.add(new Pais(
                "Brasil",
                "Brasilia",
                "Americas",
                210000000L,
                Map.of("BRL", new DetalleMoneda("Brazilian real", "R$")),
                Map.of("por", "Portuguese")
        ));

        this.paises.add(new Pais(
                "Chile",
                "Santiago",
                "Americas",
                19000000L,
                Map.of("CLP", new DetalleMoneda("Chilean peso", "$")),
                Map.of("spa", "Spanish")
        ));

        this.paises.add(new Pais(
                "España",
                "Madrid",
                "Europe",
                47000000L,
                Map.of("EUR", new DetalleMoneda("Euro", "€")),
                Map.of("spa", "Spanish")
        ));

        this.paises.add(new Pais(
                "Francia",
                "París",
                "Europe",
                67000000L,
                Map.of("EUR", new DetalleMoneda("Euro", "€")),
                Map.of("fra", "French")
        ));

        this.paises.add(new Pais(
                "Japón",
                "Tokio",
                "Asia",
                125000000L,
                Map.of("JPY", new DetalleMoneda("Japanese yen", "¥")),
                Map.of("jpn", "Japanese")
        ));
    }

    public List<Pais> getTodos() {
        //return this.paises;
        //return List.copyOf(this.paises);   // Copia inmutable
        return new ArrayList<>(this.paises); // Copia
    }

    public int cantidad() {
        return this.paises.size();
    }

    public Optional<Pais> buscarPorNombre(String nombre) {
        return this.paises.stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst();
    }

    public Optional<Pais> buscarPorCapital(String capital) {
        return this.paises.stream()
                .filter(p -> p.getCapital().equals(capital))
                .findFirst();
    }

    public List<Pais> buscarPorRegion(String region) {
        return this.paises.stream()
                .filter(p -> p.getRegion().equals(region))
                .collect(Collectors.toList());
    }

    public Optional<Pais> primerPaisPoblacionMayorA(long limite) {
        var nuevoArray = new ArrayList<Pais>();
        for (Pais p : this.paises) {
            if (p.getPoblacion() > limite) {
                return Optional.of(p);
            }
        }
        return Optional.of(null);
    }

    public String nombresConcatenados() {
        return this.paises.stream()
                .map(Pais::getNombre)
                .collect(Collectors.joining(", "));
    }

    public Optional<Pais> paisMasPoblado() {
        return this.paises.stream()
                .max(Comparator.comparing(Pais::getPoblacion));
    }

    public long poblacionTotal() {
        return this.paises.stream()
                .mapToLong(Pais::getPoblacion)
                .sum();
    }

    public String nombresConcatenador() {
        return this.paises.stream()
                .map(Pais::getNombre)
                .collect(Collectors.joining(", "));
    }

    public Map<String, Long> contarPorRegion() {
        return this.paises.stream()
                .collect(Collectors.groupingBy(
                        Pais::getRegion,
                        Collectors.counting()
                ));
    }

    public Map<String, Long> contadorPorRegion2() {
        Map<String, Long> result = new HashMap<>();
        for (Pais p : this.paises) {
            String key = p.getRegion();
            Long value = result.get(key);
            if (value == null) {
                result.put(key, 1L);
                continue;
            }
            result.put(key, value + 1);
        }
        return result;
    }

    public List<Pais> paisesOrdenadosAlfabeticamente() {
        return this.paises.stream()
                .sorted(Comparator.comparing(Pais::getNombre))
                //.sorted(Comparator.comparing(Pais::getNombre).reversed()) // Ordenar al reves
                .toList();
    }

    public List<Pais> buscarPorMoneda(String codigoMoneda) {
        return this.paises.stream()
                .filter(p -> p.getMonedas().containsKey(codigoMoneda))
                .toList();
    }

    public Set<String> todosLosIdiomas() {
        return this.paises.stream()
                .flatMap(p -> p.getIdiomas().values().stream())
                .collect(Collectors.toSet());
        /*
        País 1: stream("Spanish")             ┐
        País 2: stream("Portuguese")          │
        País 3: stream("Spanish")             │ flatMap los junta:
        País 4: stream("Spanish")             │ → stream("Spanish", "Portuguese",
        País 5: stream("French")              │           "Spanish", "Spanish",
        País 6: stream("Japanese")            ┘           "French", "Japanese")
        * */
    }

    public Map<String, List<Pais>> paisesAgrupadosPorMonedaPrincipal() {
        return this.paises.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getMonedas().keySet().iterator().next()
                ));
    }

}
