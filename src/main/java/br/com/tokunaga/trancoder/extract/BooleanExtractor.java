package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;

import br.com.tokunaga.trancoder.annotation.BooleanField;
import br.com.tokunaga.trancoder.processor.BooleanFieldProcessor;
import br.com.tokunaga.trancoder.processor.FieldProcessor;

/**
 * The BooleanExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link BooleanFieldProcessor} from a {@link Field} annotated with {@link BooleanField}.
 */
public class BooleanExtractor extends AnnotationExtractor<BooleanField> {

  public BooleanExtractor() {
    super(BooleanField.class);
  }

  @Override
  protected FieldProcessor createProcessor(final BooleanField field) {
    return new BooleanFieldProcessor(
        field.size(),
        field.trueCase(),
        field.falseCase(),
        field.padChar(),
        field.leftPad(),
        field.falseIfNull()
    );
  }
}
