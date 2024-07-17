package br.com.tokunaga.trancoder.util;

import java.math.BigInteger;

import br.com.tokunaga.trancoder.annotation.NumericField;

public class BigIntegerHolder {

  @NumericField(size = 100)
  private BigInteger value;

  public BigIntegerHolder(final BigInteger value) {
    this.value = value;
  }

  public BigInteger getValue() {
    return value;
  }
}
