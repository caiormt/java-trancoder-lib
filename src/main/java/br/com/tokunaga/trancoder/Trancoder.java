package br.com.tokunaga.trancoder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

public abstract class Trancoder {

  private static final String DATE = "00.00.0000";
  private static final String DATETIME = "00.00.0000.00-00-00-000000";
  private static final String EMPTY = "";
  private static final String SPACE = " ";
  private static final String ZERO = "0";

  private Trancoder() {
    super();
  }

  public static String convert(
      final String value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean spaceIfNull) {

    final String str = safeValue(value, stringNullDefault(spaceIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Integer value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, numericNullDefault(zeroIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Long value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, numericNullDefault(zeroIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Short value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, numericNullDefault(zeroIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Byte value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, numericNullDefault(zeroIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final BigInteger value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, numericNullDefault(zeroIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Double value,
      final int size,
      final int precision,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, precision, zeroIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Float value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, numericNullDefault(zeroIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final BigDecimal value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, numericNullDefault(zeroIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Date value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean defaultIfNull) {

    final String str = safeValue(value, dateNullDefault(defaultIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final LocalDate value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean defaultIfNull) {

    final String str = safeValue(value, dateNullDefault(defaultIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final LocalDateTime value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean defaultIfNull) {

    final String str = safeValue(value, dateTimeNullDefault(defaultIfNull));
    return safePadValue(str, size, padChar, leftPad);
  }

  private static String safeValue(final Object value, final String nullDefault) {
    return Objects.toString(value, nullDefault);
  }

  private static String safeValue(final Double value, final int precision, final boolean zeroIfNull) {
    final NumberFormat nf = formatNumberWithPrecision(precision);
    if (Objects.isNull(value))
      return zeroIfNull ? nf.format(0d) : EMPTY;
    return nf.format(value);
  }

  private static NumberFormat formatNumberWithPrecision(final int precision) {
    final NumberFormat nf = NumberFormat.getNumberInstance();
    nf.setMaximumFractionDigits(precision);
    nf.setMinimumFractionDigits(precision);
    nf.setGroupingUsed(false);
    nf.setRoundingMode(RoundingMode.HALF_EVEN);
    return nf;
  }

  private static String dateNullDefault(final boolean defaultIfNull) {
    return defaultIfNull ? DATE : EMPTY;
  }

  private static String dateTimeNullDefault(final boolean defaultIfNull) {
    return defaultIfNull ? DATETIME : EMPTY;
  }

  private static String numericNullDefault(final boolean zeroIfNull) {
    return zeroIfNull ? ZERO : EMPTY;
  }

  private static String stringNullDefault(final boolean spaceIfNull) {
    return spaceIfNull ? SPACE : EMPTY;
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
