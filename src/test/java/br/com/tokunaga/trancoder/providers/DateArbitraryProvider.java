package br.com.tokunaga.trancoder.providers;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;
import net.jqwik.time.api.Dates;

public class DateArbitraryProvider implements ArbitraryProvider {

  @Override
  public boolean canProvideFor(final TypeUsage targetType) {
    return targetType.isAssignableFrom(Date.class);
  }

  @Override
  public Set<Arbitrary<?>> provideFor(final TypeUsage targetType, final SubtypeProvider subtypeProvider) {
    return Collections.singleton(Dates.datesAsDate());
  }
}
