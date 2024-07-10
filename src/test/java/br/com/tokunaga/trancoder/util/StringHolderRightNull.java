package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.annotation.StringField;

public class StringHolderRightNull {

  @StringField(size = 100, padChar = '0', spaceIfNull = true)
  private String string;

  public StringHolderRightNull(final String string) {
    this.string = string;
  }
}
