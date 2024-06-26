package br.com.tokunaga.trancoder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

public abstract class Trancoder {

  private Trancoder() {
    super();
  }

  public static String convert(final String value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final Integer value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final Long value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final Short value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final Byte value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final Double value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final Float value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final BigInteger value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final BigDecimal value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final Date value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final LocalDate value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(final LocalDateTime value, final int size, final char padChar, final boolean leftPad) {
    final String str = safeValue(value);
    return safePadValue(str, size, padChar, leftPad);
  }

  private static String safeValue(final Object value) {
    return Objects.toString(value, StringUtils.EMPTY);
  }

  private static String padValue(final String str, final int size, final char padChar, final boolean leftPad) {
    return leftPad ? StringUtils.leftPad(str, size, padChar) : StringUtils.rightPad(str, size, padChar);
  }

  private static boolean isPadOverflow(final String str, final int size) {
    return str.length() > size;
  }

  private static String safePadValue(final String str, final int size, final char padChar, final boolean leftPad) {
    final String value = padValue(str, size, padChar, leftPad);
    if (isPadOverflow(value, size))
      throw new TrancodeOverflowException();
    return value;
  }
}
