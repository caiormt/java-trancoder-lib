package br.com.tokunaga.trancoder.processor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.stream.Stream;

import org.apache.commons.lang3.reflect.TypeUtils;

import br.com.tokunaga.trancoder.Trancoder;

public class TimeFieldProcessor extends FieldProcessor {

  private final String pattern;
  private final boolean defaultIfNull;

  public TimeFieldProcessor(
      final int size,
      final String pattern,
      final char padChar,
      final boolean leftPad,
      final boolean defaultIfNull) {

    super(size, padChar, leftPad);
    this.pattern = pattern;
    this.defaultIfNull = defaultIfNull;
  }

  public String pattern() {
    return pattern;
  }

  public boolean defaultIfNull() {
    return defaultIfNull;
  }

  @Override
  public boolean supports(final Class<?> cls) {
    return Stream.of(Date.class, LocalTime.class, LocalDateTime.class)
                 .anyMatch(type -> TypeUtils.isAssignable(cls, type));
  }

  @Override
  public String trancode(final Object value) {
    if (TypeUtils.isInstance(value, Date.class))
      return Trancoder.convert((Date) value, size(), pattern(), padChar(), leftPad(), defaultIfNull());

    if (TypeUtils.isInstance(value, LocalTime.class))
      return Trancoder.convert((LocalTime) value, size(), pattern(), padChar(), leftPad(), defaultIfNull());

    if (TypeUtils.isInstance(value, LocalDateTime.class))
      return Trancoder.convert((LocalDateTime) value, size(), pattern(), padChar(), leftPad(), defaultIfNull());

    return Trancoder.convert((Date) null, size(), pattern(), padChar(), leftPad(), defaultIfNull());
  }
}
