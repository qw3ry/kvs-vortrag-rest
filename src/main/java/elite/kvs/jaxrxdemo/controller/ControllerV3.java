package elite.kvs.jaxrxdemo.controller;

import elite.kvs.jaxrxdemo.model.Course;
import elite.kvs.jaxrxdemo.model.Professor;
import elite.kvs.jaxrxdemo.model.Uni;
import elite.kvs.jaxrxdemo.repository.DataRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("v3")
public class ControllerV3 {

  private final DataRepository data;
  private final ControllerV2 old;

  @Autowired
  public ControllerV3(DataRepository data) {
    this.data = data;
    this.old = new ControllerV2(data);
  }

  @GET
  @Path("courses/{id}")
  public Response getCourseById(@PathParam("id") int id, @QueryParam("fields") Set<String> fields) {
    Course course = data.courses().byId(id);
    return Response.ok(Util.filterFields(course, fields)).build();
  }

  @GET
  @Path("professors/{id}")
  public Response getProfById(@PathParam("id") int id, @QueryParam("fields") Set<String> fields) {
    Professor prof = data.profs().byId(id);
    return Response.ok(Util.filterFields(prof, fields)).build();
  }

  @GET
  @Path("unis/{id}")
  public Response getUniById(@PathParam("id") int id, @QueryParam("fields") Set<String> fields) {
    Uni uni = data.unis().byId(id);
    return Response.ok(Util.filterFields(uni, fields)).build();
  }

  @GET
  @Path("courses")
  public Response getCourses(@Context UriInfo uriInfo) {
    MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
    return Response.ok(Util.prepareCollection(
        data.courses().filter(Util.reflectionFilter(Course.class, params))
            .collect(Collectors.toList()), uriInfo)).build();
  }

  @GET
  @Path("professors")
  public Response getProfs(@Context UriInfo uriInfo) {
    MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
    return Response.ok(Util.prepareCollection(
        data.profs().filter(Util.reflectionFilter(Professor.class, params))
            .collect(Collectors.toList()), uriInfo)).build();
  }

  @GET
  @Path("unis")
  public Response getUnis(@Context UriInfo uriInfo) {
    return Response.ok(Util.prepareCollection(data.unis().all(), uriInfo)).build();
  }

  // region parent

  @POST
  @Path("professors")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addProf(Professor prof) throws URISyntaxException {
    return old.addProf(prof);
  }

  @PUT
  @Path("professors/{id}")
  public Response changeProf(@PathParam("id") int id, Map<String, String> putMap) {
    return old.changeProf(id, putMap);
  }

  @DELETE
  @Path("professors/{id}")
  public Response deleteProf(@PathParam("id") int id) {
    return old.deleteProf(id);
  }

  // endregion

}
