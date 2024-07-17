package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.DateField;
import br.com.tokunaga.trancoder.processor.DateFieldProcessor;
import br.com.tokunaga.trancoder.processor.FieldProcessor;

/**
 * The DateExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link DateFieldProcessor} from a {@link Field} annotated with {@link DateField}.
 */
public class DateExtractor implements Extractor {

  @Override
  public FieldProcessor extract(final Field field) {
    final DateField date = field.getAnnotation(DateField.class);
    return Objects.nonNull(date) ? createDateProperty(date) : null;
  }

  private static DateFieldProcessor createDateProperty(final DateField field) {
    return new DateFieldProcessor(
        field.size(),
        field.pattern(),
        field.padChar(),
        field.leftPad(),
        field.defaultIfNull()
    );
  }
}
