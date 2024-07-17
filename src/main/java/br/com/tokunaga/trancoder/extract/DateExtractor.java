package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;

import br.com.tokunaga.trancoder.annotation.DateField;
import br.com.tokunaga.trancoder.processor.DateFieldProcessor;
import br.com.tokunaga.trancoder.processor.FieldProcessor;

/**
 * The DateExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link DateFieldProcessor} from a {@link Field} annotated with {@link DateField}.
 */
public class DateExtractor extends AnnotationExtractor<DateField> {

  public DateExtractor() {
    super(DateField.class);
  }

  @Override
  protected FieldProcessor createProcessor(final DateField field) {
    return new DateFieldProcessor(
        field.size(),
        field.pattern(),
        field.padChar(),
        field.leftPad(),
        field.defaultIfNull()
    );
  }
}
