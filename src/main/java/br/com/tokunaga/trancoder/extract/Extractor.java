package br.com.tokunaga.trancoder.extract;

import java.lang.reflect.Field;

import br.com.tokunaga.trancoder.util.Property;

/**
 * The Extractor interface represents a mechanism for extracting {@link Property} from a {@link Field}.
 */
public interface Extractor {

  /**
   * Extracts a {@link Property} from a {@link Field}.
   *
   * @param field the field from which to extract the property
   *
   * @return the extracted property, or null if no property is found
   */
  Property extract(final Field field);

}
