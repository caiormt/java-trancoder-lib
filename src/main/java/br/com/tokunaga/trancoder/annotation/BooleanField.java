package br.com.tokunaga.trancoder.annotation;

import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ ElementType.FIELD })
public @interface BooleanField {

  int size() default 1;

  String trueCase() default "S";

  String falseCase() default "N";

  char padChar() default ' ';

  boolean leftPad() default false;

  boolean falseIfNull() default false;

}
