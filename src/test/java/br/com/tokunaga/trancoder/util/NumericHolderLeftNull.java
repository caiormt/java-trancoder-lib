package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class NumericHolderLeftNull<T extends Number> {

  @NumericField(size = 100)
  private T value;

  public NumericHolderLeftNull(final T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}
