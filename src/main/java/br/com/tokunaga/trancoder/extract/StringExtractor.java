package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.StringField;
import br.com.tokunaga.trancoder.processor.FieldProcessor;
import br.com.tokunaga.trancoder.processor.StringFieldProcessor;

/**
 * The StringExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link StringFieldProcessor} from a {@link Field} annotated with {@link StringField}.
 */
public class StringExtractor implements Extractor {

  @Override
  public FieldProcessor extract(final Field field) {
    final StringField str = field.getAnnotation(StringField.class);
    return Objects.nonNull(str) ? createStringProperty(str) : null;
  }

  private static StringFieldProcessor createStringProperty(final StringField field) {
    return new StringFieldProcessor(field.size(), field.padChar(), field.leftPad(), field.spaceIfNull());
  }
}
