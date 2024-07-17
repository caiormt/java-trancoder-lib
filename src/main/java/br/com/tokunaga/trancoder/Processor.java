package br.com.tokunaga.trancoder;

import java.lang.reflect.Field;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.tokunaga.trancoder.annotation.NumericField;
import br.com.tokunaga.trancoder.annotation.StringField;
import br.com.tokunaga.trancoder.exception.TrancodeFieldException;
import br.com.tokunaga.trancoder.exception.TrancodeReflectionException;
import br.com.tokunaga.trancoder.exception.TrancoderException;
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

      validateFieldMatchingProperty(property, field);

      final Object value = extractValue(field, object);
      final String trancode = trancodeValue(value, property);

      sb.append(trancode);
    }

    return sb.toString();
  }

  private static void validateFieldMatchingProperty(final Property property, final Field field) {
    final Class<?> cls = property.getClass();
    final Class<?> fieldClass = field.getType();

    if (StringProperty.class.isAssignableFrom(cls)) {
      validateStringProperty(fieldClass);
      return;
    }

    if (NumericProperty.class.isAssignableFrom(cls)) {
      validateNumericProperty(fieldClass);
      return;
    }

    throw new TrancoderException();
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

  private static String trancodeValue(final Object object, final Property property) {
    if (StringProperty.class.isAssignableFrom(property.getClass())) {
      final StringProperty str = (StringProperty) property;
      return Trancoder.convert((String) object, str.size(), str.padChar(), str.leftPad(), str.spaceIfNull());
    }

    if (NumericProperty.class.isAssignableFrom(property.getClass())) {
      final NumericProperty str = (NumericProperty) property;
      return Trancoder.convert((Integer) object, str.size(), str.padChar(), str.leftPad(), str.zeroIfNull());
    }

    return StringUtils.EMPTY;
  }

  private static StringProperty createStringProperty(final StringField field) {
    return new StringProperty(field.size(), field.padChar(), field.leftPad(), field.spaceIfNull());
  }

  private static NumericProperty createNumericProperty(final NumericField field) {
    return new NumericProperty(field.size(), field.padChar(), field.leftPad(), field.zeroIfNull());
  }

  private static void validateStringProperty(final Class<?> cls) {
    if (!String.class.isAssignableFrom(cls))
      throw new TrancodeFieldException();
  }

  private static void validateNumericProperty(final Class<?> cls) {
    if (!Number.class.isAssignableFrom(cls))
      throw new TrancodeFieldException();
  }
}
