package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.StringField;
import br.com.tokunaga.trancoder.util.Property;
import br.com.tokunaga.trancoder.util.StringProperty;

/**
 * The StringExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link StringProperty} from a {@link Field} annotated with {@link StringField}.
 */
public class StringExtractor implements Extractor {

  @Override
  public Property extract(final Field field) {
    final StringField str = field.getAnnotation(StringField.class);
    return Objects.nonNull(str) ? createStringProperty(str) : null;
  }

  private static StringProperty createStringProperty(final StringField field) {
    return new StringProperty(field.size(), field.padChar(), field.leftPad(), field.spaceIfNull());
  }
}
