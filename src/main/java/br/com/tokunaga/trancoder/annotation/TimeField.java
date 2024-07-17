package br.com.tokunaga.trancoder.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ ElementType.FIELD })
public @interface TimeField {

  int size();

  String pattern() default "HH:mm:ss";

  char padChar() default ' ';

  boolean leftPad() default false;

  boolean defaultIfNull() default false;

}
