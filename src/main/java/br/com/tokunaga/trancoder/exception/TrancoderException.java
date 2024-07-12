package br.com.tokunaga.trancoder.exception;

public class TrancoderException extends RuntimeException {

  public TrancoderException() {
    super();
  }

  public TrancoderException(final Exception cause) {
    super(cause);
  }
}
