package br.com.tokunaga.trancoder.processor;

import java.math.BigInteger;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.reflect.TypeUtils;

import br.com.tokunaga.trancoder.Trancoder;
import br.com.tokunaga.trancoder.exception.TrancodeFieldException;

public class NumericFieldProcessor extends FieldProcessor {

  private final boolean zeroIfNull;

  public NumericFieldProcessor(final int size, final char padChar, final boolean leftPad, final boolean zeroIfNull) {
    super(size, padChar, leftPad);
    this.zeroIfNull = zeroIfNull;
  }

  public boolean zeroIfNull() {
    return zeroIfNull;
  }

  @Override
  public boolean supports(final Class<?> cls) {
    return Stream.of(Number.class, Byte.class, Short.class, Integer.class, Long.class, BigInteger.class)
                 .anyMatch(type -> TypeUtils.isAssignable(cls, type));
  }

  @Override
  public String trancode(final Object value) {
    if (Objects.isNull(value))
      return Trancoder.convert((Byte) null, size(), padChar(), leftPad(), zeroIfNull());

    if (TypeUtils.isInstance(value, Byte.class))
      return Trancoder.convert((Byte) value, size(), padChar(), leftPad(), zeroIfNull());

    if (TypeUtils.isInstance(value, Short.class))
      return Trancoder.convert((Short) value, size(), padChar(), leftPad(), zeroIfNull());

    if (TypeUtils.isInstance(value, Integer.class))
      return Trancoder.convert((Integer) value, size(), padChar(), leftPad(), zeroIfNull());

    if (TypeUtils.isInstance(value, Long.class))
      return Trancoder.convert((Long) value, size(), padChar(), leftPad(), zeroIfNull());

    if (TypeUtils.isInstance(value, BigInteger.class))
      return Trancoder.convert((BigInteger) value, size(), padChar(), leftPad(), zeroIfNull());

    throw new TrancodeFieldException();
  }
}
