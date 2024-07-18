package br.com.tokunaga.trancoder;

import java.lang.reflect.Field;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.tokunaga.trancoder.exception.TrancoderFieldException;
import br.com.tokunaga.trancoder.exception.TrancoderReflectionException;
import br.com.tokunaga.trancoder.extract.DefaultExtractor;
import br.com.tokunaga.trancoder.extract.Extractor;
import br.com.tokunaga.trancoder.processor.FieldProcessor;

public abstract class Processor {

  private Processor() {
    super();
  }

  public static String trancode(final Object object) {
    return trancode(object, new DefaultExtractor());
  }

  public static String trancode(final Object object, final Extractor extractor) {
    if (Objects.isNull(object))
      return StringUtils.EMPTY;

    final Class<?> cls = object.getClass();
    final StringBuilder sb = new StringBuilder();
    for (final Field field : FieldUtils.getAllFieldsList(cls)) {

      final FieldProcessor processor = extractor.extract(field);
      if (Objects.isNull(processor))
        continue;

      if (!processor.supports(field.getType()))
        throw new TrancoderFieldException();

      final Object value = extractValue(field, object);
      final String trancode = processor.trancode(value);

      sb.append(trancode);
    }

    return sb.toString();
  }

  private static Object extractValue(final Field field, final Object object) {
    try {
      return FieldUtils.readField(field, object, true);
    }
    catch (final IllegalAccessException ex) {
      throw new TrancoderReflectionException();
    }
  }
}
