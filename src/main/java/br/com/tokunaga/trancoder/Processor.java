package br.com.tokunaga.trancoder;

import java.lang.reflect.Field;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.tokunaga.trancoder.annotation.NumericField;
import br.com.tokunaga.trancoder.annotation.StringField;
import br.com.tokunaga.trancoder.exception.TrancodeFieldException;
import br.com.tokunaga.trancoder.exception.TrancodeReflectionException;
import br.com.tokunaga.trancoder.util.NumericProperty;
import br.com.tokunaga.trancoder.util.Property;
import br.com.tokunaga.trancoder.util.StringProperty;

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

      final Property property = extractProperty(field);
      if (Objects.isNull(property))
        continue;

      if (!property.supports(field.getType()))
        throw new TrancodeFieldException();

      final Object value = extractValue(field, object);
      final String trancode = property.trancode(value);

      sb.append(trancode);
    }

    return sb.toString();
  }

  private static Property extractProperty(final Field field) {
    final StringField str = field.getAnnotation(StringField.class);
    if (Objects.nonNull(str))
      return createStringProperty(str);

    final NumericField number = field.getAnnotation(NumericField.class);
    if (Objects.nonNull(number))
      return createNumericProperty(number);

    return null;
  }

  private static Object extractValue(final Field field, final Object object) {
    try {
      return FieldUtils.readField(field, object, true);
    }
    catch (final IllegalAccessException ex) {
      throw new TrancodeReflectionException();
    }
  }

  private static StringProperty createStringProperty(final StringField field) {
    return new StringProperty(field.size(), field.padChar(), field.leftPad(), field.spaceIfNull());
  }

  private static NumericProperty createNumericProperty(final NumericField field) {
    return new NumericProperty(field.size(), field.padChar(), field.leftPad(), field.zeroIfNull());
  }
}
