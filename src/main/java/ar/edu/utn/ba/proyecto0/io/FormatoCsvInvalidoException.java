package ar.edu.utn.ba.proyecto0.io;

import lombok.Getter;

public class FormatoCsvInvalidoException extends RuntimeException {
  @Getter
  private final long numLinea;
  @Getter
  private final String linea;

  public FormatoCsvInvalidoException(long numLinea, String linea) {
    super("Linea problemática [" + numLinea + "]> " + linea);
    this.numLinea = numLinea;
    this.linea = linea;
  }
}
