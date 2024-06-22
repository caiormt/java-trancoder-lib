package br.com.tokunaga.trancoder;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class Trancoder {

  public Trancoder() {
    super();
  }

  protected String convert(final String string, final int size, final boolean leftPad) {
    final String value = safeValue(string);
    return leftPad ? StringUtils.leftPad(value, size, ' ') : StringUtils.rightPad(value, size, ' ');
  }

  private static String safeValue(final String string) {
    return Objects.nonNull(string) ? string : StringUtils.EMPTY;
  }
}
