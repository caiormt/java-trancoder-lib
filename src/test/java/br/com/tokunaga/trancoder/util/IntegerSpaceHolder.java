package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class IntegerSpaceHolder {

  @NumericField(size = 100, padChar = ' ', zeroIfNull = false)
  private Integer value;

  public IntegerSpaceHolder(final Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
