package br.com.tokunaga.trancoder.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ ElementType.FIELD })
public @interface DateTimeField {

  int size();

  String pattern() default "dd.MM.yyyy-HH:mm:ss";

  char padChar() default ' ';

  boolean leftPad() default false;

  boolean defaultIfNull() default false;

}
