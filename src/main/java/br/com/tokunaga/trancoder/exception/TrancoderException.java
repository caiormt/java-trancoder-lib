package br.com.tokunaga.trancoder.exception;

public class TrancoderException extends RuntimeException {

  public TrancoderException() {
    super();
  }

  public TrancoderException(final String message) {
    super(message);
  }

  public TrancoderException(final Exception cause) {
    super(cause);
  }
}
