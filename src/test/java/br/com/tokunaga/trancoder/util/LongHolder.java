package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class LongHolder {

  @NumericField(size = 100)
  private Long value;

  public LongHolder(final Long value) {
    this.value = value;
  }

  public Long getValue() {
    return value;
  }
}
