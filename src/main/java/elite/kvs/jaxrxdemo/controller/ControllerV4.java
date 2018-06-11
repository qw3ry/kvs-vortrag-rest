package elite.kvs.jaxrxdemo.controller;

import elite.kvs.jaxrxdemo.model.Course;
import elite.kvs.jaxrxdemo.model.LinkedObject;
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
@Path("v4")
public class ControllerV4 {

  private final DataRepository data;

  @Autowired
  public ControllerV4(DataRepository data) {
    this.data = data;
  }

  @GET
  @Path("courses/{id}")
  public Response getCourseById(@PathParam("id") int id, @QueryParam("fields") Set<String> fields) {
    Course course = data.courses().byId(id);
    return Response.ok(new LinkedObject<>(
        Util.filterFields(course, fields),
        course.createLink("v4", "self"))).build();
  }

  @GET
  @Path("professors/{id}")
  public Response getProfById(@PathParam("id") int id, @QueryParam("fields") Set<String> fields) {
    Professor prof = data.profs().byId(id);
    return Response.ok(new LinkedObject<>(
        Util.filterFields(prof, fields),
        prof.createLink("v4", "self"))).build();
  }

  @GET
  @Path("unis/{id}")
  public Response getUniById(@PathParam("id") int id, @QueryParam("fields") Set<String> fields) {
    Uni uni = data.unis().byId(id);
    return Response.ok(new LinkedObject<>(
        Util.filterFields(uni, fields),
        uni.createLink("v4", "self"))).build();
  }

  @GET
  @Path("courses")
  public Response getCourses(@Context UriInfo uriInfo) {
    MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
    return Response.ok(Util.prepareLinkedCollection(
        data.courses().filter(Util.reflectionFilter(Course.class, params))
            .collect(Collectors.toList()), "v4", uriInfo)).build();
  }

  @GET
  @Path("professors")
  public Response getProfs(@Context UriInfo uriInfo) {
    MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
    return Response.ok(Util.prepareLinkedCollection(
        data.profs().filter(Util.reflectionFilter(Professor.class, params))
            .collect(Collectors.toList()), "v4", uriInfo)).build();
  }

  @GET
  @Path("unis")
  public Response getUnis(@Context UriInfo uriInfo) {
    throw new RuntimeException();
    //return Response.ok(Util.prepareLinkedCollection(data.unis().all(), "v4", uriInfo)).build();
  }

}
