package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.DecimalField;
import br.com.tokunaga.trancoder.util.DecimalProperty;
import br.com.tokunaga.trancoder.util.Property;

/**
 * The DecimalExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link DecimalProperty} from a {@link Field} annotated with {@link DecimalField}.
 */
public class DecimalExtractor implements Extractor {

  @Override
  public Property extract(final Field field) {
    final DecimalField number = field.getAnnotation(DecimalField.class);
    return Objects.nonNull(number) ? createDecimalProperty(number) : null;
  }

  private static DecimalProperty createDecimalProperty(final DecimalField field) {
    return new DecimalProperty(field.size(), field.precision(), field.padChar(), field.leftPad(), field.zeroIfNull());
  }
}
