package br.com.tokunaga.trancoder;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

public abstract class Trancoder {

  private Trancoder() {
    super();
  }

  public static String convert(final String value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    final String pad = padValue(str, size, padChar, leftPad);
    if (isPadOverflow(pad, size))
      throw new TrancodeOverflowException();

    return pad;
  }

  public static String convert(final Integer value, final int size) {
    final String str = safeValue(value);
    return StringUtils.rightPad(str, size, '0');
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

  private static String safeValue(final Integer value) {
    return Objects.nonNull(value) ? Integer.toString(value) : StringUtils.EMPTY;
  }
}
