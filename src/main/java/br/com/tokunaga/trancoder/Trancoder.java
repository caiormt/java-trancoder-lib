package br.com.tokunaga.trancoder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

public abstract class Trancoder {

  private static final Date DEFAULT_DATE =
      new Date(0L);

  private static final LocalDate DEFAULT_LOCALDATE =
      LocalDate.of(1, 1, 1);

  private static final LocalTime DEFAULT_LOCALTIME =
      LocalTime.MIDNIGHT;

  private static final LocalDateTime DEFAULT_LOCALDATETIME =
      LocalDateTime.of(DEFAULT_LOCALDATE, DEFAULT_LOCALTIME);

  private static final TimeZone DEFAULT_TIMEZONE =
      TimeZone.getTimeZone("GMT");

  private Trancoder() {
    super();
  }

  public static String convert(
      final String value,
      final int size,
      final char padChar,
      final boolean leftPad,
      final boolean spaceIfNull) {

    final String str = safeValue(value, spaceIfNull);
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
      final String pattern,
      final char padChar,
      final boolean leftPad,
      final boolean defaultIfNull) {

    final String str = safeValue(value, pattern, defaultIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final LocalDateTime value,
      final int size,
      final String pattern,
      final char padChar,
      final boolean leftPad,
      final boolean defaultIfNull) {

    final String str = safeValue(value, pattern, defaultIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  public static String convert(
      final Boolean value,
      final int size,
      final String trueCase,
      final String falseCase,
      final char padChar,
      final boolean leftPad,
      final boolean falseIfNull) {

    final String str = safeValue(value, trueCase, falseCase, falseIfNull);
    return safePadValue(str, size, padChar, leftPad);
  }

  private static String safeValue(final String value, final boolean spaceIfNull) {
    return Objects.toString(value, nullValue(spaceIfNull));
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

  private static String safeValue(final TemporalAccessor value, final String pattern, final boolean defaultIfNull) {
    final DateTimeFormatter dtf = formatTemporalWithPattern(pattern);
    return Objects.nonNull(value) ? dtf.format(value) : nullValue(defaultIfNull, dtf);
  }

  private static String safeValue(
      final Boolean value,
      final String trueCase,
      final String falseCase,
      final boolean falseIfNull) {

    return Objects.nonNull(value)
           ? value ? trueCase : falseCase
           : nullValue(falseIfNull, falseCase);
  }

  private static String nullValue(final boolean spaceIfNull) {
    return spaceIfNull ? StringUtils.SPACE : StringUtils.EMPTY;
  }

  private static String nullValue(final boolean defaultIfNull, final SimpleDateFormat simpleDateFormat) {
    return defaultIfNull ? simpleDateFormat.format(DEFAULT_DATE) : StringUtils.EMPTY;
  }

  private static String nullValue(final boolean zeroIfNull, final NumberFormat numberFormat) {
    return zeroIfNull ? numberFormat.format(NumberUtils.DOUBLE_ZERO) : StringUtils.EMPTY;
  }

  private static String nullValue(final boolean defaultIfNull, final DateTimeFormatter dateTimeFormatter) {
    return defaultIfNull ? dateTimeFormatter.format(DEFAULT_LOCALDATETIME) : StringUtils.EMPTY;
  }

  private static String nullValue(final boolean falseIfNull, final String falseCase) {
    return falseIfNull ? falseCase : StringUtils.EMPTY;
  }

  private static SimpleDateFormat formatDateWithPattern(final String pattern) {
    final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    sdf.setTimeZone(DEFAULT_TIMEZONE);
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

  private static DateTimeFormatter formatTemporalWithPattern(final String pattern) {
    return DateTimeFormatter.ofPattern(pattern);
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
