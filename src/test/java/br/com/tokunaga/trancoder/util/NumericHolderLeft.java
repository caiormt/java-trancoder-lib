package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class NumericHolderLeft<T extends Number> {

  @NumericField(size = 100, padChar = ' ', zeroIfNull = false)
  private T value;

  public NumericHolderLeft(final T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}
