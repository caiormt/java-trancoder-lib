package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.StringField;

public class Bar {

  @StringField(size = 100)
  private Long value;

  public Bar(final Long value) {
    this.value = value;
  }

  public Long getValue() {
    return value;
  }
}
