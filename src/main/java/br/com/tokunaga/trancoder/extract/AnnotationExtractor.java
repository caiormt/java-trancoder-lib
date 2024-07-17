package br.com.tokunaga.trancoder.extract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

import br.com.tokunaga.trancoder.processor.FieldProcessor;

public abstract class AnnotationExtractor<T extends Annotation> implements Extractor {

  private final Class<T> annotationClass;

  public AnnotationExtractor(final Class<T> annotationClass) {
    this.annotationClass = annotationClass;
  }

  protected abstract FieldProcessor createProcessor(final T annotation);

  @Override
  public FieldProcessor extract(final Field field) {
    final T annotation = field.getAnnotation(annotationClass);
    return Objects.nonNull(annotation) ? createProcessor(annotation) : null;
  }
}
