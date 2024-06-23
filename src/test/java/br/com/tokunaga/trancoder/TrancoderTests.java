package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NotEmpty;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

class TrancoderTests {

  @Property
  void shouldConvertNullString(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((String) null, size, padChar, false))
        .isEqualTo(pad);

    assertThat(Trancoder.convert((String) null, size, padChar, true))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullInteger(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Integer) null, size, padChar, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullLong(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Long) null, size, padChar, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullShort(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Short) null, size, padChar, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullByte(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert((Byte) null, size, padChar, false))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertEmptyString(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert("", size, padChar, false))
        .isEqualTo(pad);

    assertThat(Trancoder.convert("", size, padChar, true))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertAnyString(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(str, str.length() + size, padChar, false))
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
    assertThat(Trancoder.convert(value, length + size, padChar, false))
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
    assertThat(Trancoder.convert(value, length + size, padChar, false))
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
    assertThat(Trancoder.convert(value, length + size, padChar, false))
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
    assertThat(Trancoder.convert(value, length + size, padChar, false))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldConvertAnyStringLeftPadded(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String pad = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(str, str.length() + size, padChar, true))
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
    assertThat(Trancoder.convert(value, length + size, padChar, true))
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
    assertThat(Trancoder.convert(value, length + size, padChar, true))
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
    assertThat(Trancoder.convert(value, length + size, padChar, true))
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
    assertThat(Trancoder.convert(value, length + size, padChar, true))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldThrowOverflowConvertingAnyStringExceedingSize(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    int length = str.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(str, target, padChar, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(str, target, padChar, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyIntegerExceedingSize(
      @ForAll @NotEmpty final Integer value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Integer.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyLongExceedingSize(
      @ForAll @NotEmpty final Long value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Long.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyShortExceedingSize(
      @ForAll @NotEmpty final Short value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Short.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }

  @Property
  void shouldThrowOverflowConvertingAnyByteExceedingSize(
      @ForAll @NotEmpty final Byte value,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = Byte.toString(value);
    int length = expected.length();
    int target = length - size;
    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(value, target, padChar, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }
}
