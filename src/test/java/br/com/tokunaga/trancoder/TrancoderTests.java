package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.Example;
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

  @Example
  void shouldConvertNullString() {
    assertThat(trancoder.convert(null, 0))
        .isEqualTo("");
  }

  @Property
  void shouldConvertNullStringSized(@ForAll @IntRange(min = 1, max = 1000) final int size) {
    String expected = StringUtils.rightPad("", size, ' ');
    assertThat(trancoder.convert(null, size))
        .isEqualTo(expected);
  }

  @Example
  void shouldConvertEmptyString() {
    assertThat(trancoder.convert("", 0))
        .isEqualTo("");
  }

  @Property
  void shouldConvertEmptyStringSized(@ForAll @IntRange(min = 1, max = 1000) final int size) {
    String expected = StringUtils.rightPad("", size, ' ');
    assertThat(trancoder.convert("", size))
        .isEqualTo(expected);
  }

  @Property
  void shouldConvertAnyString(@ForAll @NotEmpty final String str) {
    assertThat(trancoder.convert(str, str.length()))
        .isEqualTo(str);
  }

  @Property
  void shouldConvertAnyStringSized(
      @ForAll @NotEmpty final String str,
      @ForAll @IntRange(min = 1, max = 1000) final int size) {

    String expected = StringUtils.rightPad("", size, ' ');
    assertThat(trancoder.convert(str, str.length() + size))
        .isEqualTo(str + expected);
  }
}
