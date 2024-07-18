package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.StringField;

public class StringHolderRight {

  @StringField(size = 100)
  private String string;

  public StringHolderRight(final String string) {
    this.string = string;
  }

  public String getString() {
    return string;
  }
}
