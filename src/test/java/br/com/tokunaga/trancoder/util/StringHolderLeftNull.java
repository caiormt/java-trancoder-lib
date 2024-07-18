package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.StringField;

public class StringHolderLeftNull {

  @StringField(size = 100, padChar = '0', leftPad = true, spaceIfNull = true)
  private String string;

  public StringHolderLeftNull(final String string) {
    this.string = string;
  }

  public String getString() {
    return string;
  }
}
