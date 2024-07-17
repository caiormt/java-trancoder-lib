package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.NumericField;
import br.com.tokunaga.trancoder.processor.FieldProcessor;
import br.com.tokunaga.trancoder.processor.NumericFieldProcessor;

/**
 * The NumericExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link NumericFieldProcessor} from a {@link Field} annotated with {@link NumericField}.
 */
public class NumericExtractor implements Extractor {

  @Override
  public FieldProcessor extract(final Field field) {
    final NumericField number = field.getAnnotation(NumericField.class);
    return Objects.nonNull(number) ? createNumericProperty(number) : null;
  }

  private static NumericFieldProcessor createNumericProperty(final NumericField field) {
    return new NumericFieldProcessor(field.size(), field.padChar(), field.leftPad(), field.zeroIfNull());
  }
}
