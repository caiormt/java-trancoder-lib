package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.DateField;
import br.com.tokunaga.trancoder.util.DateProperty;
import br.com.tokunaga.trancoder.util.Property;

/**
 * The DateExtractor class is an implementation of the {@link Extractor} interface that extracts a {@link DateProperty}
 * from a {@link Field} annotated with {@link DateField}.
 */
public class DateExtractor implements Extractor {

  @Override
  public Property extract(final Field field) {
    final DateField number = field.getAnnotation(DateField.class);
    return Objects.nonNull(number) ? createDateProperty(number) : null;
  }

  private static DateProperty createDateProperty(final DateField field) {
    return new DateProperty(field.size(), field.pattern(), field.padChar(), field.leftPad(), field.defaultIfNull());
  }
}
