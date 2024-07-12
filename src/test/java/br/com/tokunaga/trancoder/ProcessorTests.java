package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.StringLength;

import br.com.tokunaga.trancoder.util.Foo;
import br.com.tokunaga.trancoder.util.StringHolderLeft;
import br.com.tokunaga.trancoder.util.StringHolderLeftNull;
import br.com.tokunaga.trancoder.util.StringHolderRight;
import br.com.tokunaga.trancoder.util.StringHolderRightNull;

class ProcessorTests {

  @Example
  void shouldTrancodeNullObject() {
    assertThat(Processor.trancode(null))
        .isEmpty();
  }

  @Property
  void shouldIgnoreFieldWithoutAnnotation(@ForAll final String string) {
    final Foo foo = new Foo(string);
    assertThat(Processor.trancode(foo))
        .isEmpty();
  }

  @Property
  void shouldTrancodeStringField(@ForAll @StringLength(100) final String string) {
    final StringHolderRight stringHolder = new StringHolderRight(string);
    assertThat(Processor.trancode(stringHolder))
        .isEqualTo(string);
  }

  @Property
  void shouldTrancodeStringFieldRightPadding(@ForAll @StringLength(max = 100) final String string) {
    final int length = string.length();
    final String pad = StringUtils.repeat(' ', 100 - length);
    final StringHolderRight stringHolder = new StringHolderRight(string);
    assertThat(Processor.trancode(stringHolder))
        .isEqualTo(string + pad);
  }

  @Property
  void shouldTrancodeStringFieldLeftPadding(@ForAll @StringLength(max = 100) final String string) {
    final int length = string.length();
    final String pad = StringUtils.repeat(' ', 100 - length);
    final StringHolderLeft stringHolder = new StringHolderLeft(string);
    assertThat(Processor.trancode(stringHolder))
        .isEqualTo(pad + string);
  }

  @Example
  void shouldTrancodeStringFieldNullRightPadding() {
    final String pad = StringUtils.repeat('0', 99);
    final StringHolderRightNull stringHolder = new StringHolderRightNull(null);
    assertThat(Processor.trancode(stringHolder))
        .isEqualTo(" " + pad);
  }

  @Example
  void shouldTrancodeStringFieldNullLeftPadding() {
    final String pad = StringUtils.repeat('0', 99);
    final StringHolderLeftNull stringHolder = new StringHolderLeftNull(null);
    assertThat(Processor.trancode(stringHolder))
        .isEqualTo(pad + " ");
  }

  @Property
  void shouldTrancodeStringFieldZeroPadRightPadding(@ForAll @StringLength(max = 99) final String string) {
    final int length = string.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final StringHolderRightNull stringHolder = new StringHolderRightNull(string);
    assertThat(Processor.trancode(stringHolder))
        .isEqualTo(string + pad);
  }

  @Property
  void shouldTrancodeStringFieldZeroPadLeftPadding(@ForAll @StringLength(max = 99) final String string) {
    final int length = string.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final StringHolderLeftNull stringHolder = new StringHolderLeftNull(string);
    assertThat(Processor.trancode(stringHolder))
        .isEqualTo(pad + string);
  }
}
