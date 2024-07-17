package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.DecimalField;

public class DoubleHolder {

  @DecimalField(size = 100)
  private Double value;

  public DoubleHolder(final Double value) {
    this.value = value;
  }

  public Double getValue() {
    return value;
  }
}
