package br.com.tokunaga.trancoder.util;

import java.util.Date;

import br.com.tokunaga.trancoder.annotation.DateField;

public class DateHolder {

  @DateField(size = 100)
  private Date value;

  public DateHolder(final Date value) {
    this.value = value;
  }

  public Date getValue() {
    return value;
  }
}
