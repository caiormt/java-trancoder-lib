package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.StringLength;

import br.com.tokunaga.trancoder.exception.TrancodeFieldException;
import br.com.tokunaga.trancoder.util.Foo;
import br.com.tokunaga.trancoder.util.MismatchNumeric;
import br.com.tokunaga.trancoder.util.MismatchString;
import br.com.tokunaga.trancoder.util.NumericHolderLeft;
import br.com.tokunaga.trancoder.util.NumericHolderLeftNull;
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
  void shouldThrowFieldExceptionOnMismatchField(@ForAll final Long value) {
    final MismatchString mismatchString = new MismatchString(value);
    assertThatThrownBy(() -> Processor.trancode(mismatchString))
        .isExactlyInstanceOf(TrancodeFieldException.class);
  }

  @Property
  void shouldThrowFieldExceptionOnMismatchFieldNumeric(@ForAll final String string) {
    final MismatchNumeric mismatchNumeric = new MismatchNumeric(string);
    assertThatThrownBy(() -> Processor.trancode(mismatchNumeric))
        .isExactlyInstanceOf(TrancodeFieldException.class);
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
  void shouldTrancodeByteField(@ForAll final Byte value) {
    final String expected = Byte.toString(value);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final NumericHolderLeftNull<Byte> numericHolder = new NumericHolderLeftNull<>(value);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeShortField(@ForAll final Short value) {
    final String expected = Short.toString(value);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final NumericHolderLeftNull<Short> numericHolder = new NumericHolderLeftNull<>(value);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeIntegerField(@ForAll final Integer integer) {
    final String expected = Integer.toString(integer);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final NumericHolderLeftNull<Integer> numericHolder = new NumericHolderLeftNull<Integer>(integer) {};
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeLongField(@ForAll final Long value) {
    final String expected = Long.toString(value);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final NumericHolderLeftNull<Long> numericHolder = new NumericHolderLeftNull<>(value);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeBigIntegerField(@ForAll final BigInteger value) {
    final String expected = value.toString();
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final NumericHolderLeftNull<BigInteger> numericHolder = new NumericHolderLeftNull<>(value);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + expected);
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

  @Example
  void shouldTrancodeByteFieldNullLeftPadding() {
    final String pad = StringUtils.repeat('0', 99);
    final NumericHolderLeftNull<Byte> numericHolder = new NumericHolderLeftNull<>(null);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + "0");
  }

  @Example
  void shouldTrancodeShortFieldNullLeftPadding() {
    final String pad = StringUtils.repeat('0', 99);
    final NumericHolderLeftNull<Short> numericHolder = new NumericHolderLeftNull<>(null);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + "0");
  }

  @Example
  void shouldTrancodeIntegerFieldNullLeftPadding() {
    final String pad = StringUtils.repeat('0', 99);
    final NumericHolderLeftNull<Integer> numericHolder = new NumericHolderLeftNull<>(null);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + "0");
  }

  @Example
  void shouldTrancodeLongFieldNullLeftPadding() {
    final String pad = StringUtils.repeat('0', 99);
    final NumericHolderLeftNull<Long> numericHolder = new NumericHolderLeftNull<>(null);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + "0");
  }

  @Example
  void shouldTrancodeBigIntegerFieldNullLeftPadding() {
    final String pad = StringUtils.repeat('0', 99);
    final NumericHolderLeftNull<BigInteger> numericHolder = new NumericHolderLeftNull<>(null);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + "0");
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

  @Property
  void shouldTrancodeIntegerFieldSpacePadLeftPadding(@ForAll final Integer integer) {
    final String expected = Integer.toString(integer);
    final int length = expected.length();
    final String pad = StringUtils.repeat(' ', 100 - length);
    final NumericHolderLeft<Integer> numericHolder = new NumericHolderLeft<>(integer);
    assertThat(Processor.trancode(numericHolder))
        .isEqualTo(pad + expected);
  }
}
