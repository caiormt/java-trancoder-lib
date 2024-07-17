package br.com.tokunaga.trancoder.util;

import java.util.Date;

import br.com.tokunaga.trancoder.annotation.DateField;

public class DateHolderLeftNull<T extends Date> {

  @DateField(size = 100)
  private T value;

  public DateHolderLeftNull(final T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}
