package elite.kvs.jaxrxdemo.controller;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable exception) {
    if (WebApplicationException.class.isAssignableFrom(exception.getClass())) {
      Response r = ((WebApplicationException) exception).getResponse();
      return Response.fromResponse(r)
          .entity(new ErrorResponse(r.getStatus(), exception.getMessage())).build();
    } else {
      return Response.serverError().entity(new ErrorResponse(500, exception.getMessage())).build();
    }
  }

  private class ErrorResponse {

    public final int code;
    public final String message;

    private ErrorResponse(int code, String message) {
      this.code = code;
      this.message = message;
    }
  }
}
