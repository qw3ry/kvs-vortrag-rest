package elite.kvs.jaxrxdemo.model;

public class Error {
  public final int httpStatusCode;
  public final String message;

  public Error(int httpStatusCode, String message) {
    this.httpStatusCode = httpStatusCode;
    this.message = message;
  }
}
