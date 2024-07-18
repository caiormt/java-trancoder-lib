package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class ShortHolder {

  @NumericField(size = 100)
  private Short value;

  public ShortHolder(final Short value) {
    this.value = value;
  }

  public Short getValue() {
    return value;
  }
}
