package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.BooleanField;

public class BooleanHolder {

  @BooleanField(size = 100)
  private Boolean value;

  public BooleanHolder(final Boolean value) {
    this.value = value;
  }

  public Boolean getValue() {
    return value;
  }
}
