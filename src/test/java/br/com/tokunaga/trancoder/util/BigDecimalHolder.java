package br.com.tokunaga.trancoder.util;

import java.math.BigDecimal;

import br.com.tokunaga.trancoder.annotation.DecimalField;

public class BigDecimalHolder {

  @DecimalField(size = 100)
  private BigDecimal value;

  public BigDecimalHolder(final BigDecimal value) {
    this.value = value;
  }

  public BigDecimal getValue() {
    return value;
  }
}
