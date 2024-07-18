package br.com.tokunaga.trancoder.processor;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.apache.commons.lang3.reflect.TypeUtils;

import br.com.tokunaga.trancoder.Trancoder;

public class DecimalFieldProcessor extends FieldProcessor {

  private final int precision;
  private final boolean zeroIfNull;

  public DecimalFieldProcessor(
      final int size,
      final int precision,
      final char padChar,
      final boolean leftPad,
      final boolean zeroIfNull) {

    super(size, padChar, leftPad);
    this.precision = precision;
    this.zeroIfNull = zeroIfNull;
  }

  public int precision() {
    return precision;
  }

  public boolean zeroIfNull() {
    return zeroIfNull;
  }

  @Override
  public boolean supports(final Class<?> cls) {
    return Stream.of(Float.class, Double.class, BigDecimal.class)
                 .anyMatch(type -> TypeUtils.isAssignable(cls, type));
  }

  @Override
  public String trancode(final Object value) {
    if (TypeUtils.isInstance(value, Float.class))
      return Trancoder.convert((Float) value, size(), precision(), padChar(), leftPad(), zeroIfNull());

    if (TypeUtils.isInstance(value, Double.class))
      return Trancoder.convert((Double) value, size(), precision(), padChar(), leftPad(), zeroIfNull());

    if (TypeUtils.isInstance(value, BigDecimal.class))
      return Trancoder.convert((BigDecimal) value, size(), precision(), padChar(), leftPad(), zeroIfNull());

    return Trancoder.convert((Float) null, size(), precision(), padChar(), leftPad(), zeroIfNull());
  }
}
