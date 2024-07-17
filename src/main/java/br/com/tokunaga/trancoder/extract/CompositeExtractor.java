package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import br.com.tokunaga.trancoder.processor.FieldProcessor;

/**
 * The CompositeExtractor class is an implementation of the {@link Extractor} interface that combines multiple
 * Extractors to extract {@link FieldProcessor properties} from a {@link Field}.
 */
public class CompositeExtractor implements Extractor {

  private final List<Extractor> delegates;

  public CompositeExtractor(final List<Extractor> delegates) {
    this.delegates = delegates;
  }

  @Override
  public FieldProcessor extract(final Field field) {
    for (final Extractor extractor : delegates) {
      final FieldProcessor fieldProcessor = extractor.extract(field);
      if (Objects.nonNull(fieldProcessor))
        return fieldProcessor;
    }
    return null;
  }
}
