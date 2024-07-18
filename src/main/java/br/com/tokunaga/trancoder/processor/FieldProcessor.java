package br.com.tokunaga.trancoder.processor;

public abstract class FieldProcessor {

  private final int size;
  private final char padChar;
  private final boolean leftPad;

  protected FieldProcessor(final int size, final char padChar, final boolean leftPad) {
    this.size = size;
    this.padChar = padChar;
    this.leftPad = leftPad;
  }

  public abstract boolean supports(final Class<?> cls);

  public abstract String trancode(final Object value);

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
