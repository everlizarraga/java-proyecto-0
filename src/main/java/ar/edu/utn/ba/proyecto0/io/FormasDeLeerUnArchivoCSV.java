package ar.edu.utn.ba.proyecto0.io;

import ar.edu.utn.ba.proyecto0.modelo.Pais;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FormasDeLeerUnArchivoCSV {

  static public void probarFormas(String path) throws IOException {
    formaA_StringUnico(path);
    formaB_ListaDeLineas(path);
    formaC_StreamEficiente(path);
  }

  static private void formaA_StringUnico(String path) throws IOException {
    /*
    readString te devuelve todo el archivo como un String único.
    Útil para archivos chicos.
    Para CSVs grandes, no escala — cargás todo en memoria.
    * */
    System.out.println("IO_FORMA_A-StringUnico =====================");
    String contenido = Files.readString(Path.of(path)); // alternativa> Paths.get(path)
    System.out.println(contenido);
  }

  static private void formaB_ListaDeLineas(String path) throws IOException {
    /*
    readAllLines te devuelve una List<String> con cada línea.
    Útil para archivos chicos a medianos.
    * */
    System.out.println("IO_FORMA_B-ListaDeLineas =====================");
    List<String> listaString = Files.readAllLines(Path.of(path)); // alternativa> Paths.get(path)
    listaString.forEach(s -> System.out.println("> " + s));
    //listaString.forEach(System.out::println);
  }

  static private void formaC_StreamEficiente(String path) throws IOException {
    /*
    Files.lines te da un Stream<String> perezoso —
    lee línea por línea según las necesitás,
    sin cargar todo el archivo en memoria.
    Es lo más eficiente para archivos grandes.
    * */
    System.out.println("\nIO_FORMA_C-StreamEficiente =====================");
    try (Stream<String> lineas = Files.lines(Path.of(path))) {
      lineas.skip(1).forEach(s -> System.out.println("* " + s));
    }
  }

}
