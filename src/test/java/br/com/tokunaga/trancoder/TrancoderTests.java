package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
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
    assertThat(trancoder.convert(null))
        .isEqualTo("");
  }

  @Example
  void shouldConvertEmptyString() {
    assertThat(trancoder.convert(""))
        .isEqualTo("");
  }

  @Property
  void shouldConvertAnyString(@ForAll @NotEmpty final String str) {
    assertThat(trancoder.convert(str))
        .isEqualTo(str);
  }
}
