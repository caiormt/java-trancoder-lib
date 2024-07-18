package br.com.tokunaga.trancoder.util;

import java.util.Date;

import br.com.tokunaga.trancoder.annotation.TimeField;

public class TimeHolder {

  @TimeField(size = 100)
  private Date value;

  public TimeHolder(final Date value) {
    this.value = value;
  }

  public Date getValue() {
    return value;
  }
}
