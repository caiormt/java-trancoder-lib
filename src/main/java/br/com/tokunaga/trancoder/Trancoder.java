package br.com.tokunaga.trancoder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

public abstract class Trancoder {

  public static final Date NULL_DATE = new Date(0L);

  private static final String DATE = "00.00.0000";
  private static final String DATETIME = "00.00.0000.00-00-00-000000";
  private static final String EMPTY = "";
  private static final String SPACE = " ";

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

    final String str = safeValue(value, zeroIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Long value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, zeroIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Short value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, zeroIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Byte value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, zeroIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final BigInteger value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, zeroIfNull);
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
      final int precision,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, precision, zeroIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final BigDecimal value,
      final int size,
      final int precision,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    final String str = safeValue(value, precision, zeroIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Date value,
      final int size,
      final String pattern,
      final char padChar,
      final boolean leftPad,
      final boolean defaultIfNull) {

    final String str = safeValue(value, pattern, defaultIfNull);
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

  private static String safeValue(final Date value, final String pattern, final boolean defaultIfNull) {
    final SimpleDateFormat sdf = formatDateWithPattern(pattern);
    return Objects.nonNull(value) ? sdf.format(value) : nullValue(defaultIfNull, sdf);
  }

  private static String safeValue(final Number value, final boolean zeroIfNull) {
    return safeValue(value, 0, zeroIfNull);
  }

  private static String safeValue(final Number value, final int precision, final boolean zeroIfNull) {
    final NumberFormat nf = formatNumberWithPrecision(precision);
    return Objects.nonNull(value) ? nf.format(value) : nullValue(zeroIfNull, nf);
  }

  private static String nullValue(final boolean defaultIfNull, final SimpleDateFormat numberFormat) {
    return defaultIfNull ? numberFormat.format(NULL_DATE) : EMPTY;
  }

  private static String nullValue(final boolean zeroIfNull, final NumberFormat numberFormat) {
    return zeroIfNull ? numberFormat.format(NumberUtils.DOUBLE_ZERO) : EMPTY;
  }

  private static SimpleDateFormat formatDateWithPattern(final String pattern) {
    final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    return sdf;
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
