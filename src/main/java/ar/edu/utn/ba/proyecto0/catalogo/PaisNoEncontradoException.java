package ar.edu.utn.ba.proyecto0.catalogo;

import lombok.Getter;

public class PaisNoEncontradoException extends RuntimeException {
  @Getter
  private final String nombreBuscado;

  public PaisNoEncontradoException(String nombreBuscado) {
    super("País no encontrado: " + nombreBuscado);
    this.nombreBuscado = nombreBuscado;
  }
}
