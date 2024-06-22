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
    final String padded = padValue(size, padChar, leftPad, str);
    if (padded.length() > size)
      throw new TrancodeOverflowException();

    return padded;
  }

  private static String padValue(final int size, final char padChar, final boolean leftPad, final String str) {
    return leftPad ? StringUtils.leftPad(str, size, padChar) : StringUtils.rightPad(str, size, padChar);
  }

  private static String safeValue(final String str) {
    return Objects.nonNull(str) ? str : StringUtils.EMPTY;
  }
}
