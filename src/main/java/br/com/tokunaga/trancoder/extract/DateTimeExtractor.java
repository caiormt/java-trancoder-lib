package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;

import br.com.tokunaga.trancoder.annotation.DateTimeField;
import br.com.tokunaga.trancoder.processor.DateTimeFieldProcessor;
import br.com.tokunaga.trancoder.processor.FieldProcessor;

/**
 * The DateTimeExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link DateTimeFieldProcessor} from a {@link Field} annotated with {@link DateTimeField}.
 */
public class DateTimeExtractor extends AnnotationExtractor<DateTimeField> {

  public DateTimeExtractor() {
    super(DateTimeField.class);
  }

  @Override
  protected FieldProcessor createProcessor(final DateTimeField field) {
    return new DateTimeFieldProcessor(
        field.size(),
        field.pattern(),
        field.padChar(),
        field.leftPad(),
        field.defaultIfNull()
    );
  }
}
