package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.annotation.DecimalField;
import br.com.tokunaga.trancoder.processor.DecimalFieldProcessor;
import br.com.tokunaga.trancoder.processor.FieldProcessor;

/**
 * The DecimalExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link DecimalFieldProcessor} from a {@link Field} annotated with {@link DecimalField}.
 */
public class DecimalExtractor implements Extractor {

  @Override
  public FieldProcessor extract(final Field field) {
    final DecimalField number = field.getAnnotation(DecimalField.class);
    return Objects.nonNull(number) ? createDecimalProperty(number) : null;
  }

  private static DecimalFieldProcessor createDecimalProperty(final DecimalField field) {
    return new DecimalFieldProcessor(
        field.size(),
        field.precision(),
        field.padChar(),
        field.leftPad(),
        field.zeroIfNull()
    );
  }
}
