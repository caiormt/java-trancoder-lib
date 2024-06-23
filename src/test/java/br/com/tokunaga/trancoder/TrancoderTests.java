package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.Example;
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

    String expected = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(null, size, padChar, false))
        .isEqualTo(expected);

    assertThat(Trancoder.convert(null, size, padChar, true))
        .isEqualTo(expected);
  }

  @Example
  void shouldConvertNullInteger() {
    assertThat(Trancoder.convert(null, 0))
        .isEqualTo("");
  }

  @Property
  void shouldConvertNullIntegerSized(@ForAll @IntRange(min = 1, max = 1000) final int size) {
    String expected = StringUtils.repeat('0', size);
    assertThat(Trancoder.convert(null, size))
        .isEqualTo(expected);
  }

  @Property
  void shouldConvertEmptyString(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert("", size, padChar, false))
        .isEqualTo(expected);

    assertThat(Trancoder.convert("", size, padChar, true))
        .isEqualTo(expected);
  }

  @Property
  void shouldConvertAnyString(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(str, str.length() + size, padChar, false))
        .isEqualTo(str + expected);
  }

  @Property
  void shouldConvertAnyInteger(@ForAll final Integer value) {
    String expected = Integer.toString(value);
    final int length = expected.length();
    assertThat(Trancoder.convert(value, length))
        .isEqualTo(expected);
  }

  @Property
  void shouldConvertAnyIntegerSized(
      @ForAll final Integer value,
      @ForAll @IntRange(min = 1, max = 1000) final int size) {

    String str = Integer.toString(value);
    String expected = StringUtils.repeat('0', size);
    final int length = Integer.toString(value).length();
    assertThat(Trancoder.convert(value, length + size))
        .isEqualTo(str + expected);
  }

  @Property
  void shouldConvertAnyStringLeftPadded(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = StringUtils.repeat(padChar, size);
    assertThat(Trancoder.convert(str, str.length() + size, padChar, true))
        .isEqualTo(expected + str);
  }

  @Property
  void shouldThrowOverflowConvertingAnyStringExceedingSize(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    final int length = str.length();
    final int selectSize = length - size;
    assertThatThrownBy(() -> Trancoder.convert(str, selectSize, padChar, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> Trancoder.convert(str, selectSize, padChar, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }
}
