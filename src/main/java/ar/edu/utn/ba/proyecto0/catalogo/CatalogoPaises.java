package ar.edu.utn.ba.proyecto0.catalogo;

import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        for (Pais p : this.paises) {
            if (p.getNombre().equals(nombre)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Optional<Pais> buscarPorCapital(String capital) {
        for (Pais p : this.paises) {
            if(capital.equals(p.getCapital())) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<Pais> buscarPorRegion(String region) {
        List<Pais> resultados = new ArrayList<>();
        for (Pais p : this.paises) {
            if (p.getRegion().equals(region)) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    public Optional<Pais> primerPaisPoblacionMayorA(long limite) {
        var nuevoArray = new ArrayList<Pais>();
        for(Pais p : this.paises) {
            if(p.getPoblacion() > limite) {
                return Optional.of(p);
            }
        }
        return Optional.of(null);
    }

}
