package br.com.tokunaga.trancoder.util;

public class NumericProperty extends Property {

  private final boolean zeroIfNull;

  public NumericProperty(final int size, final char padChar, final boolean leftPad, final boolean zeroIfNull) {
    super(size, padChar, leftPad);
    this.zeroIfNull = zeroIfNull;
  }

  public boolean zeroIfNull() {
    return zeroIfNull;
  }
}
