package elite.kvs.jaxrxdemo.controller;

import elite.kvs.jaxrxdemo.model.Professor;
import elite.kvs.jaxrxdemo.repository.DataRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("v2")
public class ControllerV2 {

  private final DataRepository data;
  private final ControllerV1 old;

  @Autowired
  public ControllerV2(DataRepository data) {
    this.data = data;
    this.old = new ControllerV1(data);
  }

  @POST
  @Path("professors")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addProf(Professor prof) throws URISyntaxException {
    data.profs().put(prof);
    return Response.created(new URI("/v2/professors/" + prof.id)).entity(prof).build();
  }

  @PUT
  @Path("professors/{id}")
  public Response changeProf(@PathParam("id") int id, Map<String, String> putMap) {
    Professor p = data.profs().byId(id);
    if (putMap.containsKey("name")) {
      p.name = putMap.get("name");
    }
    if (putMap.containsKey("uniId")) {
      p.uniId = Integer.valueOf(putMap.get("uniId"));
    }
    return Response.ok(p).build();
  }

  @DELETE
  @Path("professors/{id}")
  public Response deleteProf(@PathParam("id") int id) {
    return Response.ok(data.profs().delete(id)).build();
  }

  //region parent

  @GET
  @Path("courses/{id}")
  public Response getCourseById(@PathParam("id") int id) {
    return old.getCourseById(id);
  }

  @GET
  @Path("professors/{id}")
  public Response getProfById(@PathParam("id") int id) {
    return old.getProfById(id);
  }

  @GET
  @Path("unis/{id}")
  public Response getUniById(@PathParam("id") int id) {
    return old.getUniById(id);
  }

  @GET
  @Path("courses")
  public Response getCourses(@Context UriInfo uriInfo) {
    return old.getCourses(uriInfo);
  }

  @GET
  @Path("professors")
  public Response getProfs(@Context UriInfo uriInfo) {
    return old.getProfs(uriInfo);
  }

  @GET
  @Path("unis")
  public Response getUnis() {
    return old.getUnis();
  }

  //endregion

}
