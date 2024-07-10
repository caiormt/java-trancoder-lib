package br.com.tokunaga.trancoder;

import java.lang.reflect.Field;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.tokunaga.trancoder.annotation.StringField;

public abstract class Processor {

  private Processor() {
    super();
  }

  public static String trancode(final Object object) {
    if (Objects.isNull(object))
      return StringUtils.EMPTY;

    final Class<?> cls = object.getClass();
    final StringBuilder sb = new StringBuilder();
    for (final Field field : FieldUtils.getAllFieldsList(cls)) {

      final StringField ann = field.getAnnotation(StringField.class);
      if (Objects.isNull(ann))
        continue;

      final Object value = extractValue(field, object);
      if (Objects.nonNull(value) && !String.class.isAssignableFrom(value.getClass()))
        continue; // TODO error

      final String trancode = Trancoder.convert(
          (String) value,
          ann.size(),
          ann.padChar(),
          ann.leftPad(),
          ann.spaceIfNull()
      );

      sb.append(trancode);
    }

    return sb.toString();
  }

  private static Object extractValue(final Field field, final Object object) {
    try {
      return FieldUtils.readField(field, object, true);
    }
    catch (final IllegalAccessException ex) {
      throw new RuntimeException(ex);
    }
  }
}
