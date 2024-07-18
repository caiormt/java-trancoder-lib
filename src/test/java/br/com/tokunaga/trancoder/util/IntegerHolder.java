package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class IntegerHolder {

  @NumericField(size = 100)
  private Integer value;

  public IntegerHolder(final Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
