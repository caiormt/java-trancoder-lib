package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;

import br.com.tokunaga.trancoder.annotation.DecimalField;
import br.com.tokunaga.trancoder.processor.DecimalFieldProcessor;
import br.com.tokunaga.trancoder.processor.FieldProcessor;

/**
 * The DecimalExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link DecimalFieldProcessor} from a {@link Field} annotated with {@link DecimalField}.
 */
public class DecimalExtractor extends AnnotationExtractor<DecimalField> {

  public DecimalExtractor() {
    super(DecimalField.class);
  }

  @Override
  protected FieldProcessor createProcessor(final DecimalField field) {
    return new DecimalFieldProcessor(
        field.size(),
        field.precision(),
        field.padChar(),
        field.leftPad(),
        field.zeroIfNull()
    );
  }
}
