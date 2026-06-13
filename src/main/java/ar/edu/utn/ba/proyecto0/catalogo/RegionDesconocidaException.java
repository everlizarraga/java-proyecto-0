package ar.edu.utn.ba.proyecto0.catalogo;

import lombok.Getter;

import java.util.List;

public class RegionDesconocidaException extends RuntimeException {
  @Getter
  private final String region;
  @Getter
  private final List<String> regionesDisponibles;

  public RegionDesconocidaException(String region, List<String> regionesDisponibles) {
    super("Región desconocida: " + region);
    this.region = region;
    this.regionesDisponibles = regionesDisponibles;
  }
}
