package ar.edu.utn.ba.proyecto0;

import ar.edu.utn.ba.proyecto0.catalogo.CatalogoPaises;
import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        CatalogoPaises catalogo = new CatalogoPaises();
        forma1(catalogo);
        forma2(catalogo);
        forma3(catalogo);
        forma4(catalogo);
        forma5(catalogo);

        /*
         * Quiero hacer algo solo si hay valor - (ej: imprimir)ifPresent(...)
         * Quiero un valor por defecto si está vacío - orElse(...)
         * Quiero abortar con excepción si está vacío - orElseThrow(...)
         * Quiero transformar el valor (extraer propiedad, formatear) - map(...).orElse(...)
         * Quiero usar el valor en muchas líneas de código condicionales - isPresent() + get() (única vez justificada)
         *
         * Regla práctica: evitá isPresent() + get() excepto cuando realmente lo necesitás.
         * Es la forma menos idiomática. El resto (ifPresent, orElse, map) leen mucho más limpio.
         * */

        System.out.println("===");
        catalogo.buscarPorCapital("Madrid")
                .ifPresent(p -> System.out.println("Capital encontrada: " + p));

        System.out.println("====");
        System.out.println("\n=== Países de Americas ===");
        List<Pais> americanos = catalogo.buscarPorRegion("Americas");
        System.out.println("Encontré " + americanos.size() + " países:");
        for (Pais p : americanos) {
            System.out.println("  - " + p.getNombre());
        }

        System.out.println("\n=== Países de Antarctica ===");
        List<Pais> antarticos = catalogo.buscarPorRegion("Antarctica");
        System.out.println("Encontré " + antarticos.size() + " países.");

        System.out.println("\n===");
        catalogo.primerPaisPoblacionMayorA(100_000_000L)
                .ifPresent(p -> System.out.println("Primero con >100M: " + p.getNombre()));

        System.out.println("===");
        //catalogo.buscarPorRegion("Marte");
        catalogo.buscarPorRegion("Marte");
    }

    private static void forma1(CatalogoPaises catalogo) {
        // ============================================
        // FORMA 1: isPresent() + get() — la más obvia, no la más elegante
        // ============================================
        System.out.println("=== Forma 1: isPresent + get ===");
        Optional<Pais> resultado1 = catalogo.buscarPorNombre("Argentina");
        System.out.println(">>> " + resultado1);
        //if(resultado1.isEmpty()) {
        if (resultado1.isPresent()) {
            Pais pais = resultado1.get();
            System.out.println("Encontre: " + pais);
        } else {
            System.out.println("No existe");
        }
    }

    private static void forma2(CatalogoPaises catalogo) {
        // ============================================
        // FORMA 2: ifPresent() — más limpia cuando solo querés "hacer algo si hay"
        // ============================================
        System.out.println("\n=== Forma 2: ifPresent ===");
        catalogo.buscarPorNombre("Brasil")
                .ifPresent(p -> System.out.println("Encontre: " + p));
        catalogo.buscarPorNombre("Atlantis")
                .ifPresent(p -> System.out.println("Encontre: " + p));
    }

    private static void forma3(CatalogoPaises catalogo) {
        // ============================================
        // FORMA 3: orElse() — valor por defecto si está vacío
        // ============================================
        System.out.println("\n=== Forma 3: orElse ===");
        Pais paisOrDefault = catalogo.buscarPorNombre("Atlantis")
                .orElse(new Pais("Desconocido", "(sin capital)", "(sin región)", 0L));
        System.out.println("Resultado: " + paisOrDefault);
    }

    private static void forma4(CatalogoPaises catalogo) {
        // ============================================
        // FORMA 4: orElseThrow() — tirar excepción si no hay
        // ============================================
        System.out.println("\n=== Forma 4: orElseThrow ===");
        try {
            Pais p = catalogo.buscarPorNombre("Atlantis")
                    .orElseThrow(() -> new RuntimeException("País no encontrado: Atlantis"));
            System.out.println(p);
        } catch (RuntimeException e) {
            System.out.println("Capturé la excepción: " + e.getMessage());
        }
    }

    private static void forma5(CatalogoPaises catalogo) {
        // ============================================
        // FORMA 5: map() + orElse() — lo más parecido a ?. de JS
        // ============================================
        System.out.println("\n=== Forma 5: map + orElse ===");
        String capitalArgentina = catalogo.buscarPorNombre("Argentina")
                .map(Pais::getCapital)
                .orElse("(no encontrada)");
        System.out.println("Capital de Argentina: " + capitalArgentina);

        String capitalAtlantis = catalogo.buscarPorNombre("Atlantis")
                .map(Pais::getCapital)
                .orElse("(no encontrada)");
        System.out.println("Capital de Atlantis: " + capitalAtlantis);
    }


}
