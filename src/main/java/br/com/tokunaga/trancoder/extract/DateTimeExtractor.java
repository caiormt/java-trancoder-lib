package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.DateTimeField;
import br.com.tokunaga.trancoder.processor.DateTimeFieldProcessor;
import br.com.tokunaga.trancoder.processor.FieldProcessor;

/**
 * The DateTimeExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link DateTimeFieldProcessor} from a {@link Field} annotated with {@link DateTimeField}.
 */
public class DateTimeExtractor implements Extractor {

  @Override
  public FieldProcessor extract(final Field field) {
    final DateTimeField dateTime = field.getAnnotation(DateTimeField.class);
    return Objects.nonNull(dateTime) ? createDateTimeProperty(dateTime) : null;
  }

  private static DateTimeFieldProcessor createDateTimeProperty(final DateTimeField field) {
    return new DateTimeFieldProcessor(
        field.size(),
        field.pattern(),
        field.padChar(),
        field.leftPad(),
        field.defaultIfNull()
    );
  }
}
