package br.com.tokunaga.trancoder;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

public class Trancoder {

  public Trancoder() {
    super();
  }

  protected String convert(final String str, final int size, char padChar) {
    return convert(str, size, padChar, false);
  }

  protected String convert(final String value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    final String pad = padValue(str, size, padChar, leftPad);
    if (isPadOverflow(pad, size))
      throw new TrancodeOverflowException();

    return pad;
  }

  private static boolean isPadOverflow(final String str, final int size) {
    return str.length() > size;
  }

  private static String padValue(final String str, final int size, final char padChar, final boolean leftPad) {
    return leftPad ? StringUtils.leftPad(str, size, padChar) : StringUtils.rightPad(str, size, padChar);
  }

  private static String safeValue(final String str) {
    return Objects.nonNull(str) ? str : StringUtils.EMPTY;
  }
}
