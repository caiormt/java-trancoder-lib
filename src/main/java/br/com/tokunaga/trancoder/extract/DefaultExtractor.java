package br.com.tokunaga.trancoder.extract;

import java.util.Arrays;

public class DefaultExtractor extends CompositeExtractor {

  public DefaultExtractor() {
    super(Arrays.asList(new StringExtractor(), new NumericExtractor(), new DecimalExtractor()));
  }
}
