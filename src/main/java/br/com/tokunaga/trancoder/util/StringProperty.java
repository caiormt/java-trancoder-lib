package br.com.tokunaga.trancoder.util;

public class StringProperty extends Property {

  private final boolean spaceIfNull;

  public StringProperty(final int size, final char padChar, final boolean leftPad, final boolean spaceIfNull) {
    super(size, padChar, leftPad);
    this.spaceIfNull = spaceIfNull;
  }

  public boolean spaceIfNull() {
    return spaceIfNull;
  }
}
