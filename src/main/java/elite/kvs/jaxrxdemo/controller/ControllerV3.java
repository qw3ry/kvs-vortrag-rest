package elite.kvs.jaxrxdemo.controller;

import elite.kvs.jaxrxdemo.model.Course;
import elite.kvs.jaxrxdemo.model.Professor;
import elite.kvs.jaxrxdemo.model.Uni;
import elite.kvs.jaxrxdemo.repository.DataRepository;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("v3")
public class ControllerV3 {

  private final DataRepository data;

  @Autowired
  public ControllerV3(DataRepository data) {
    this.data = data;
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

}
