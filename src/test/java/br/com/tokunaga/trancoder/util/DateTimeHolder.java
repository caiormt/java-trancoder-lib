package br.com.tokunaga.trancoder.util;

import java.util.Date;

import br.com.tokunaga.trancoder.annotation.DateTimeField;

public class DateTimeHolder {

  @DateTimeField(size = 100)
  private Date value;

  public DateTimeHolder(final Date value) {
    this.value = value;
  }

  public Date getValue() {
    return value;
  }
}
