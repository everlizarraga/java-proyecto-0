package ar.edu.utn.ba.proyecto0.catalogo;

import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogoPaises {
    private List<Pais> paises;

    public CatalogoPaises() {
        this.paises = new ArrayList<>();
        this.paises.add(new Pais("Argentina", "Buenos Aires", "Americas", 45000000L));
        this.paises.add(new Pais("Brasil", "Brasilia", "Americas", 210000000L));
        this.paises.add(new Pais("Chile", "Santiago", "Americas", 19000000L));
        this.paises.add(new Pais("España", "Madrid", "Europe", 47000000L));
        this.paises.add(new Pais("Francia", "París", "Europe", 67000000L));
        this.paises.add(new Pais("Japón", "Tokio", "Asia", 125000000L));
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

}
