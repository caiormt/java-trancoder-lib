package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class MismatchNumeric {

  @NumericField(size = 100)
  private String value;

  public MismatchNumeric(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
