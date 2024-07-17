package br.com.tokunaga.trancoder.util;

import br.com.tokunaga.trancoder.Trancoder;

public class StringProperty extends Property {

  private final boolean spaceIfNull;

  public StringProperty(final int size, final char padChar, final boolean leftPad, final boolean spaceIfNull) {
    super(size, padChar, leftPad);
    this.spaceIfNull = spaceIfNull;
  }

  public boolean spaceIfNull() {
    return spaceIfNull;
  }

  @Override
  public boolean supports(final Class<?> cls) {
    return String.class.isAssignableFrom(cls);
  }

  @Override
  public String trancode(final Object value) {
    return Trancoder.convert((String) value, size(), padChar(), leftPad(), spaceIfNull());
  }
}
