package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NotEmpty;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

class TrancoderTests {

  private static final String DATE = "00.00.0000";
  private static final String DATETIME = "00.00.0000.00-00-00-000000";
  private static final String ZERO = "0";
  private static final String DECIMAL_ZERO = "0.00";
  private static final String SPACE = " ";

  @Property
  void shouldConvertNullString(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((String) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullStringLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((String) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullStringAsSpace(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((String) null, size, padChar, false, true))
        .isEqualTo(SPACE + pad);
  }

  @Property
  void shouldConvertNullStringAsSpaceLeftPadded(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((String) null, size, padChar, true, true))
        .isEqualTo(pad + SPACE);
  }

  @Property
  void shouldConvertNullInteger(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Integer) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullIntegerLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Integer) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullIntegerAsZero(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Integer) null, size, padChar, false, true))
        .isEqualTo(ZERO + pad);
  }

  @Property
  void shouldConvertNullIntegerAsZeroLeftPadded(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Integer) null, size, padChar, true, true))
        .isEqualTo(pad + ZERO);
  }

  @Property
  void shouldConvertNullLong(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Long) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullLongLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Long) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullLongAsZero(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Long) null, size, padChar, false, true))
        .isEqualTo(ZERO + pad);
  }

  @Property
  void shouldConvertNullLongAsZeroLeftPadded(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Long) null, size, padChar, true, true))
        .isEqualTo(pad + ZERO);
  }

  @Property
  void shouldConvertNullShort(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Short) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullShortLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Short) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullShortAsZero(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Short) null, size, padChar, false, true))
        .isEqualTo(ZERO + pad);
  }

  @Property
  void shouldConvertNullShortAsZeroLeftPadded(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Short) null, size, padChar, true, true))
        .isEqualTo(pad + ZERO);
  }

  @Property
  void shouldConvertNullByte(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Byte) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullByteLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Byte) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullByteAsZero(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Byte) null, size, padChar, false, true))
        .isEqualTo(ZERO + pad);
  }

  @Property
  void shouldConvertNullByteAsZeroLeftPadded(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Byte) null, size, padChar, true, true))
        .isEqualTo(pad + ZERO);
  }

  @Property
  void shouldConvertNullDouble(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Double) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullDoubleLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Double) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullDoubleAsZero(
      @ForAll @IntRange(min = 4, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 4);
    assertThat(Trancoder.convert((Double) null, size, padChar, false, true))
        .isEqualTo(DECIMAL_ZERO + pad);
  }

  @Property
  void shouldConvertNullDoubleAsZeroLeftPadded(
      @ForAll @IntRange(min = 4, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 4);
    assertThat(Trancoder.convert((Double) null, size, padChar, true, true))
        .isEqualTo(pad + DECIMAL_ZERO);
  }

  @Property
  void shouldConvertNullFloat(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Float) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullFloatLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Float) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullFloatAsZero(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Float) null, size, padChar, false, true))
        .isEqualTo(ZERO + pad);
  }

  @Property
  void shouldConvertNullFloatAsZeroLeftPadded(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((Float) null, size, padChar, true, true))
        .isEqualTo(pad + ZERO);
  }

  @Property
  void shouldConvertNullBigInteger(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((BigInteger) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullBigIntegerLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((BigInteger) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullBigIntegerAsZero(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((BigInteger) null, size, padChar, false, true))
        .isEqualTo(ZERO + pad);
  }

  @Property
  void shouldConvertNullBigIntegerAsZeroLeftPadded(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((BigInteger) null, size, padChar, true, true))
        .isEqualTo(pad + ZERO);
  }

  @Property
  void shouldConvertNullBigDecimal(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((BigDecimal) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullBigDecimalLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((BigDecimal) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullBigDecimalAsZero(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((BigDecimal) null, size, padChar, false, true))
        .isEqualTo(ZERO + pad);
  }

  @Property
  void shouldConvertNullBigDecimalAsZeroLeftPadded(
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 1);
    assertThat(Trancoder.convert((BigDecimal) null, size, padChar, true, true))
        .isEqualTo(pad + ZERO);
  }

  @Property
  void shouldConvertNullDate(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Date) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullDateLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Date) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullDateAsDefault(
      @ForAll @IntRange(min = 10, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 10);
    assertThat(Trancoder.convert((Date) null, size, padChar, false, true))
        .isEqualTo(DATE + pad);
  }

  @Property
  void shouldConvertNullDateAsDefaultLeftPadded(
      @ForAll @IntRange(min = 10, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 10);
    assertThat(Trancoder.convert((Date) null, size, padChar, true, true))
        .isEqualTo(pad + DATE);
  }

  @Property
  void shouldConvertNullLocalDate(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((LocalDate) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullLocalDateLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((LocalDate) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullLocalDateAsDefault(
      @ForAll @IntRange(min = 10, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 10);
    assertThat(Trancoder.convert((LocalDate) null, size, padChar, false, true))
        .isEqualTo(DATE + pad);
  }

  @Property
  void shouldConvertNullLocalDateAsDefaultLeftPadded(
      @ForAll @IntRange(min = 10, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 10);
    assertThat(Trancoder.convert((LocalDate) null, size, padChar, true, true))
        .isEqualTo(pad + DATE);
  }

  @Property
  void shouldConvertNullLocalDateTime(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((LocalDateTime) null, size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullLocalDateTimeLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((LocalDateTime) null, size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullLocalDateTimeAsDefault(
      @ForAll @IntRange(min = 26, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 26);
    assertThat(Trancoder.convert((LocalDateTime) null, size, padChar, false, true))
        .isEqualTo(DATETIME + pad);
  }

  @Property
  void shouldConvertNullLocalDateTimeAsDefaultLeftPadded(
      @ForAll @IntRange(min = 26, max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size - 26);
    assertThat(Trancoder.convert((LocalDateTime) null, size, padChar, true, true))
        .isEqualTo(pad + DATETIME);
  }

  @Property
  void shouldConvertEmptyString(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert("", size, padChar, false, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertEmptyStringLeftPadded(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert("", size, padChar, true, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertAnyString(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(str, str.length() + size, padChar, false, false))
        .isEqualTo(str + pad);
  }

  @Property
  void shouldConvertAnyInteger(
      @ForAll final Integer value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Integer.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyLong(
      @ForAll final Long value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Long.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyShort(
      @ForAll final Short value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Short.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyByte(
      @ForAll final Byte value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Byte.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyDouble(
      @ForAll final Double value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = String.format("%.2f", value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyFloat(
      @ForAll final Float value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Float.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyBigInteger(
      @ForAll final BigInteger value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyBigDecimal(
      @ForAll final BigDecimal value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyDate(
      @ForAll final Date value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyLocalDate(
      @ForAll final LocalDate value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyLocalDateTime(
      @ForAll final LocalDateTime value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, false, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyStringLeftPadded(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(str, str.length() + size, padChar, true, false))
        .isEqualTo(pad + str);
  }

  @Property
  void shouldConvertAnyIntegerLeftPadded(
      @ForAll final Integer value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Integer.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyLongLeftPadded(
      @ForAll final Long value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Long.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyShortLeftPadded(
      @ForAll final Short value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Short.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyByteLeftPadded(
      @ForAll final Byte value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Byte.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyDoubleLeftPadded(
      @ForAll final Double value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = String.format("%.2f", value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyFloatLeftPadded(
      @ForAll final Float value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Float.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyBigIntegerLeftPadded(
      @ForAll final BigInteger value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyBigDecimalLeftPadded(
      @ForAll final BigDecimal value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyDateLeftPadded(
      @ForAll final Date value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyLocalDateLeftPadded(
      @ForAll final LocalDate value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldConvertAnyLocalDateTimeLeftPadded(
      @ForAll final LocalDateTime value,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(value, length + size, padChar, true, false))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldThrowOverflowConvertingAnyStringExceedingSize(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    int length = str.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(str, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(str, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(str, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(str, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyIntegerExceedingSize(
      @ForAll final Integer value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Integer.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyLongExceedingSize(
      @ForAll final Long value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Long.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyShortExceedingSize(
      @ForAll final Short value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Short.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyByteExceedingSize(
      @ForAll final Byte value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Byte.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyDoubleExceedingSize(
      @ForAll final Double value,
      @ForAll @IntRange(min = 4, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = String.format("%.2f", value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyFloatExceedingSize(
      @ForAll final Float value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Float.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyBigIntegerExceedingSize(
      @ForAll final BigInteger value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyBigDecimalExceedingSize(
      @ForAll final BigDecimal value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyDateExceedingSize(
      @ForAll final Date value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyLocalDateExceedingSize(
      @ForAll final LocalDate value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyLocalDateTimeExceedingSize(
      @ForAll final LocalDateTime value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = value.toString();
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }
}
