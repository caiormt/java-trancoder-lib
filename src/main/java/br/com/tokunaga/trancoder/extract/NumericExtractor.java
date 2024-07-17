package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.NumericField;
import br.com.tokunaga.trancoder.util.NumericProperty;
import br.com.tokunaga.trancoder.util.Property;

/**
 * The NumericExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link NumericProperty} from a {@link Field} annotated with {@link NumericField}.
 */
public class NumericExtractor implements Extractor {

  @Override
  public Property extract(final Field field) {
    final NumericField number = field.getAnnotation(NumericField.class);
    return Objects.nonNull(number) ? createNumericProperty(number) : null;
  }

  private static NumericProperty createNumericProperty(final NumericField field) {
    return new NumericProperty(field.size(), field.padChar(), field.leftPad(), field.zeroIfNull());
  }
}
