package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NotEmpty;
import net.jqwik.api.lifecycle.BeforeProperty;

class TrancoderTests {

  private Trancoder trancoder;

  @BeforeProperty
  void setUp() {
    trancoder = new Trancoder();
  }

  @Property
  void shouldConvertNullString(@ForAll @IntRange(max = 1000) final int size) {
    String expected = StringUtils.rightPad("", size, ' ');
    assertThat(trancoder.convert(null, size, ' ', false))
        .isEqualTo(expected);
  }

  @Property
  void shouldConvertEmptyString(@ForAll @IntRange(max = 1000) final int size) {
    String expected = StringUtils.rightPad("", size, ' ');
    assertThat(trancoder.convert("", size, ' ', false))
        .isEqualTo(expected);
  }

  @Property
  void shouldConvertAnyString(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size) {

    String expected = StringUtils.rightPad("", size, ' ');
    assertThat(trancoder.convert(str, str.length() + size, ' ', false))
        .isEqualTo(str + expected);

    assertThat(trancoder.convert(str, str.length() + size, ' '))
        .isEqualTo(str + expected);
  }

  @Property
  void shouldConvertAnyStringLeftPadded(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(max = 1000) final int size) {

    String expected = StringUtils.rightPad("", size, ' ');
    assertThat(trancoder.convert(str, str.length() + size, ' ', true))
        .isEqualTo(expected + str);
  }
}
