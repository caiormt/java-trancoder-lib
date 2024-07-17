package br.com.tokunaga.trancoder;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.FloatRange;
import net.jqwik.api.constraints.StringLength;

import br.com.tokunaga.trancoder.exception.TrancodeFieldException;
import br.com.tokunaga.trancoder.util.BigDecimalHolder;
import br.com.tokunaga.trancoder.util.BooleanHolder;
import br.com.tokunaga.trancoder.util.ByteHolder;
import br.com.tokunaga.trancoder.util.DateHolder;
import br.com.tokunaga.trancoder.util.DoubleHolder;
import br.com.tokunaga.trancoder.util.FloatHolder;
import br.com.tokunaga.trancoder.util.Foo;
import br.com.tokunaga.trancoder.util.IntegerHolder;
import br.com.tokunaga.trancoder.util.MismatchNumeric;
import br.com.tokunaga.trancoder.util.MismatchString;
import br.com.tokunaga.trancoder.util.NumericHolderLeftNull;
import br.com.tokunaga.trancoder.util.ShortHolder;
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
    final ByteHolder byteHolder = new ByteHolder(value);
    assertThat(Processor.trancode(byteHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeShortField(@ForAll final Short value) {
    final String expected = Short.toString(value);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final ShortHolder shortHolder = new ShortHolder(value);
    assertThat(Processor.trancode(shortHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeIntegerField(@ForAll final Integer integer) {
    final String expected = Integer.toString(integer);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final IntegerHolder integerHolder = new IntegerHolder(integer);
    assertThat(Processor.trancode(integerHolder))
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
  void shouldTrancodeFloatField(@ForAll @FloatRange(max = 9999f) final Float value) {
    final String expected = new DecimalFormat("0.00").format(value);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final FloatHolder floatHolder = new FloatHolder(value);
    assertThat(Processor.trancode(floatHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeDoubleField(@ForAll @DoubleRange(max = 9999d) final Double value) {
    final String expected = new DecimalFormat("0.00").format(value);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final DoubleHolder doubleHolder = new DoubleHolder(value);
    assertThat(Processor.trancode(doubleHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeBigDecimalField(@ForAll @BigRange(min = "-9999", max = "9999") final BigDecimal value) {
    final String expected = new DecimalFormat("0.00").format(value);
    final int length = expected.length();
    final String pad = StringUtils.repeat('0', 100 - length);
    final BigDecimalHolder bigDecimalHoldernumericHolder = new BigDecimalHolder(value);
    assertThat(Processor.trancode(bigDecimalHoldernumericHolder))
        .isEqualTo(pad + expected);
  }

  @Property
  void shouldTrancodeDateField(@ForAll final Date value) {
    final String expected = new SimpleDateFormat("dd.MM.yyyy").format(value);
    final int length = expected.length();
    final String pad = StringUtils.repeat(' ', 100 - length);
    final DateHolder dateHolder = new DateHolder(value);
    assertThat(Processor.trancode(dateHolder))
        .isEqualTo(expected + pad);
  }

  @Property
  void shouldTrancodeBooleanField(@ForAll final Boolean value) {
    final String expected = value ? "S" : "N";
    final int length = expected.length();
    final String pad = StringUtils.repeat(' ', 100 - length);
    final BooleanHolder booleanHolder = new BooleanHolder(value);
    assertThat(Processor.trancode(booleanHolder))
        .isEqualTo(expected + pad);
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
    final ByteHolder byteHolder = new ByteHolder(null);
    assertThat(Processor.trancode(byteHolder))
        .isEqualTo(pad + "0");
  }

  @Example
  void shouldTrancodeShortFieldNullLeftPadding() {
    final String pad = StringUtils.repeat('0', 99);
    final ShortHolder shortHolder = new ShortHolder(null);
    assertThat(Processor.trancode(shortHolder))
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
    final IntegerHolder integerHolder = new IntegerHolder(integer);
    assertThat(Processor.trancode(integerHolder))
        .isEqualTo(pad + expected);
  }
}
