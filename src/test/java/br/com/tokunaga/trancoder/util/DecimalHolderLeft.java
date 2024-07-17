package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.DecimalField;

public class DecimalHolderLeft<T extends Number> {

  @DecimalField(size = 100, padChar = ' ', zeroIfNull = false)
  private T value;

  public DecimalHolderLeft(final T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}
