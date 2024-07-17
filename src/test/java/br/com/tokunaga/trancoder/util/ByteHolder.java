package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class ByteHolder {

  @NumericField(size = 100)
  private Byte value;

  public ByteHolder(final Byte value) {
    this.value = value;
  }

  public Byte getValue() {
    return value;
  }
}
