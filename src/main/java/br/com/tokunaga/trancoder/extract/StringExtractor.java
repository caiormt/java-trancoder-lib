package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;

import br.com.tokunaga.trancoder.annotation.StringField;
import br.com.tokunaga.trancoder.processor.FieldProcessor;
import br.com.tokunaga.trancoder.processor.StringFieldProcessor;

/**
 * The StringExtractor class is an implementation of the {@link Extractor} interface that extracts a
 * {@link StringFieldProcessor} from a {@link Field} annotated with {@link StringField}.
 */
public class StringExtractor extends AnnotationExtractor<StringField> {

  public StringExtractor() {
    super(StringField.class);
  }

  @Override
  protected FieldProcessor createProcessor(final StringField field) {
    return new StringFieldProcessor(field.size(), field.padChar(), field.leftPad(), field.spaceIfNull());
  }
}
