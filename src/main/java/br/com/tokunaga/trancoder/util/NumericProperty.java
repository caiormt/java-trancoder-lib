package br.com.tokunaga.trancoder.util;

import java.math.BigInteger;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.tokunaga.trancoder.Trancoder;

public class NumericProperty extends Property {

  private final boolean zeroIfNull;

  public NumericProperty(final int size, final char padChar, final boolean leftPad, final boolean zeroIfNull) {
    super(size, padChar, leftPad);
    this.zeroIfNull = zeroIfNull;
  }

  public boolean zeroIfNull() {
    return zeroIfNull;
  }

  @Override
  public boolean supports(final Class<?> cls) {
    return Number.class.isAssignableFrom(cls);
  }

  @Override
  public String trancode(final Object value) {
    if (Objects.isNull(value))
      return Trancoder.convert((Byte) null, size(), padChar(), leftPad(), zeroIfNull());

    if (value instanceof Byte)
      return Trancoder.convert((Byte) value, size(), padChar(), leftPad(), zeroIfNull());

    if (value instanceof Short)
      return Trancoder.convert((Short) value, size(), padChar(), leftPad(), zeroIfNull());

    if (value instanceof Integer)
      return Trancoder.convert((Integer) value, size(), padChar(), leftPad(), zeroIfNull());

    if (value instanceof Long)
      return Trancoder.convert((Long) value, size(), padChar(), leftPad(), zeroIfNull());

    if (value instanceof BigInteger)
      return Trancoder.convert((BigInteger) value, size(), padChar(), leftPad(), zeroIfNull());

    return StringUtils.EMPTY;
  }
}
