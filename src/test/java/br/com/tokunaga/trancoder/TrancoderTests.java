package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.lifecycle.BeforeProperty;

import br.com.tokunaga.trancoder.exception.TrancodeOverflowException;

class TrancoderTests {

  private Trancoder trancoder;

  @BeforeProperty
  void setUp() {
    trancoder = new Trancoder();
  }

  @Property
  void shouldConvertNullString(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = StringUtils.rightPad("", size, padChar);
    assertThat(trancoder.convert(null, size, padChar, false))
        .isEqualTo(expected);

    assertThat(trancoder.convert(null, size, padChar, true))
        .isEqualTo(expected);
  }

  @Property
  void shouldConvertEmptyString(
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = StringUtils.rightPad("", size, padChar);
    assertThat(trancoder.convert("", size, padChar, false))
        .isEqualTo(expected);

    assertThat(trancoder.convert("", size, padChar, true))
        .isEqualTo(expected);
  }

  @Property
  void shouldConvertAnyString(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = StringUtils.rightPad("", size, padChar);
    assertThat(trancoder.convert(str, str.length() + size, padChar, false))
        .isEqualTo(str + expected);
  }

  @Property
  void shouldConvertAnyStringLeftPadded(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size,
      @ForAll final char padChar) {

    String expected = StringUtils.rightPad("", size, padChar);
    assertThat(trancoder.convert(str, str.length() + size, padChar, true))
        .isEqualTo(expected + str);
  }

  @Property
  void shouldThrowOverflowConvertingAnyStringExceedingSize(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(min = 1, max = 1000) final int size,
      @ForAll final char padChar) {

    assertThatThrownBy(() -> trancoder.convert(str, str.length() - size, padChar, true))
        .isExactlyInstanceOf(TrancodeOverflowException.class);

    assertThatThrownBy(() -> trancoder.convert(str, str.length() - size, padChar, false))
        .isExactlyInstanceOf(TrancodeOverflowException.class);
  }
}
