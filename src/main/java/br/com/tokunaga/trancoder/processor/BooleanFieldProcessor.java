package br.com.tokunaga.trancoder.processor;

import org.apache.commons.lang3.reflect.TypeUtils;

import br.com.tokunaga.trancoder.Trancoder;

public class BooleanFieldProcessor extends FieldProcessor {

  private final String trueCase;
  private final String falseCase;
  private final boolean falseIfNull;

  public BooleanFieldProcessor(
      final int size,
      final String trueCase,
      final String falseCase,
      final char padChar,
      final boolean leftPad,
      final boolean falseIfNull) {

    super(size, padChar, leftPad);
    this.trueCase = trueCase;
    this.falseCase = falseCase;
    this.falseIfNull = falseIfNull;
  }

  public String trueCase() {
    return trueCase;
  }

  public String falseCase() {
    return falseCase;
  }

  public boolean spaceIfNull() {
    return falseIfNull;
  }

  @Override
  public boolean supports(final Class<?> cls) {
    return TypeUtils.isAssignable(cls, Boolean.class);
  }

  @Override
  public String trancode(final Object value) {
    return Trancoder.convert((Boolean) value, size(), trueCase(), falseCase(), padChar(), leftPad(), spaceIfNull());
  }
}
