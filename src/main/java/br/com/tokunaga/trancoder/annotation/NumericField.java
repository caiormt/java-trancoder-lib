package br.com.tokunaga.trancoder.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ ElementType.FIELD })
public @interface NumericField {

  int size();

  char padChar() default '0';

  boolean leftPad() default true;

  boolean zeroIfNull() default true;

}
