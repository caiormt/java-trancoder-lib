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
    assertThat(Trancoder.convert(null, size, padChar, false))
        .isEqualTo(pad);

    assertThat(Trancoder.convert(null, size, padChar, true))
        .isEqualTo(pad);
  }

  @Property
  void shouldConvertNullInteger(@ForAll @IntRange(max = 1000) final int size) {
    String pad = StringUtils.repeat('0', size);
    assertThat(Trancoder.convert(null, size, false))
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
      @ForAll @IntRange(max = 1000) final int size) {

    String expected = Integer.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat('0', size);
    assertThat(Trancoder.convert(value, length + size, false))
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
      @ForAll @IntRange(max = 1000) final int size) {

    String expected = Integer.toString(value);
    int length = expected.length();
    String pad = StringUtils.repeat('0', size);
    assertThat(Trancoder.convert(value, length + size, true))
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
}
