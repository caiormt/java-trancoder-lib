package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.BooleanField;
import br.com.tokunaga.trancoder.util.BooleanProperty;
import br.com.tokunaga.trancoder.util.Property;

/**
 * The BooleanExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link BooleanProperty} from a {@link Field} annotated with {@link BooleanField}.
 */
public class BooleanExtractor implements Extractor {

  @Override
  public Property extract(final Field field) {
    final BooleanField str = field.getAnnotation(BooleanField.class);
    return Objects.nonNull(str) ? createBooleanProperty(str) : null;
  }

  private static BooleanProperty createBooleanProperty(final BooleanField field) {
    return new BooleanProperty(
        field.size(),
        field.trueCase(),
        field.falseCase(),
        field.padChar(),
        field.leftPad(),
        field.falseIfNull()
    );
  }
}
