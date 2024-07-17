package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.DecimalField;

public class FloatHolder {

  @DecimalField(size = 100)
  private Float value;

  public FloatHolder(final Float value) {
    this.value = value;
  }

  public Float getValue() {
    return value;
  }
}
