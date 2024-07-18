package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.StringField;

public class StringHolderLeft {

  @StringField(size = 100, leftPad = true)
  private String string;

  public StringHolderLeft(final String string) {
    this.string = string;
  }

  public String getString() {
    return string;
  }
}
