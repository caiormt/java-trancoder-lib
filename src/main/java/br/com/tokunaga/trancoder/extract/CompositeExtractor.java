package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import br.com.tokunaga.trancoder.util.Property;

/**
 * The CompositeExtractor class is an implementation of the {@link Extractor} interface that combines multiple
 * Extractors to extract {@link Property properties} from a {@link Field}.
 */
public class CompositeExtractor implements Extractor {

  private final List<Extractor> delegates;

  public CompositeExtractor(final List<Extractor> delegates) {
    this.delegates = delegates;
  }

  @Override
  public Property extract(final Field field) {
    for (final Extractor extractor : delegates) {
      final Property property = extractor.extract(field);
      if (Objects.nonNull(property))
        return property;
    }
    return null;
  }
}
