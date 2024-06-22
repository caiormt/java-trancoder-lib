package br.com.tokunaga.trancoder;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class Trancoder {

  public Trancoder() {
    super();
  }

  protected String convert(final String string) {
    return Objects.nonNull(string) ? string : StringUtils.EMPTY;
  }
}
