package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.TimeField;
import br.com.tokunaga.trancoder.processor.FieldProcessor;
import br.com.tokunaga.trancoder.processor.TimeFieldProcessor;

/**
 * The TimeExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link TimeFieldProcessor} from a {@link Field} annotated with {@link TimeField}.
 */
public class TimeExtractor implements Extractor {

  @Override
  public FieldProcessor extract(final Field field) {
    final TimeField time = field.getAnnotation(TimeField.class);
    return Objects.nonNull(time) ? createTimeProperty(time) : null;
  }

  private static TimeFieldProcessor createTimeProperty(final TimeField field) {
    return new TimeFieldProcessor(
        field.size(),
        field.pattern(),
        field.padChar(),
        field.leftPad(),
        field.defaultIfNull()
    );
  }
}
