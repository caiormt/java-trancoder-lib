package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.DecimalField;

public class DecimalHolderLeftNull<T extends Number> {

  @DecimalField(size = 100)
  private T value;

  public DecimalHolderLeftNull(final T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}
