package br.com.tokunaga.trancoder.processor;

import org.apache.commons.lang3.reflect.TypeUtils;

import br.com.tokunaga.trancoder.Trancoder;

public class StringFieldProcessor extends FieldProcessor {

  private final boolean spaceIfNull;

  public StringFieldProcessor(final int size, final char padChar, final boolean leftPad, final boolean spaceIfNull) {
    super(size, padChar, leftPad);
    this.spaceIfNull = spaceIfNull;
  }

  public boolean spaceIfNull() {
    return spaceIfNull;
  }

  @Override
  public boolean supports(final Class<?> cls) {
    return TypeUtils.isAssignable(cls, String.class);
  }

  @Override
  public String trancode(final Object value) {
    return Trancoder.convert((String) value, size(), padChar(), leftPad(), spaceIfNull());
  }
}
