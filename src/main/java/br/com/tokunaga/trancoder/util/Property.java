package br.com.tokunaga.trancoder.util;

public class Property {

  private final int size;
  private final char padChar;
  private final boolean leftPad;

  public Property(final int size, final char padChar, final boolean leftPad) {
    this.size = size;
    this.padChar = padChar;
    this.leftPad = leftPad;
  }

  public int size() {
    return size;
  }

  public char padChar() {
    return padChar;
  }

  public boolean leftPad() {
    return leftPad;
  }
}
