package elite.kvs.jaxrxdemo.controller;

import elite.kvs.jaxrxdemo.model.Professor;
import elite.kvs.jaxrxdemo.repository.DataRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("v2")
public class ControllerV2 {

  private final DataRepository repo;

  @Autowired
  public ControllerV2(DataRepository repo) {
    this.repo = repo;
  }

  @POST
  @Path("professors")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addProf(Professor prof) throws URISyntaxException {
    repo.profs().put(prof);
    return Response.created(new URI("/v2/professors/" + prof.id)).entity(prof).build();
  }

  @PUT
  @Path("professors/{id}")
  public Response changeProf(@PathParam("id") int id, Map<String, String> putMap) {
    Professor p = repo.profs().byId(id);
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
    return Response.ok(repo.profs().delete(id)).build();
  }
}
