package elite.kvs.jaxrxdemo.controller;

import elite.kvs.jaxrxdemo.model.Course;
import elite.kvs.jaxrxdemo.model.Identifiable;
import elite.kvs.jaxrxdemo.model.Professor;
import elite.kvs.jaxrxdemo.model.Uni;
import elite.kvs.jaxrxdemo.repository.DataRepository;
import java.util.stream.Collectors;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("v1")
public class ControllerV1 {

  private final DataRepository data;

  @Autowired
  public ControllerV1(DataRepository data) {
    this.data = data;
  }

  @GET
  @Path("courses/{id}")
  public Response getCourseById(@PathParam("id") int id) {
    return Response.ok(data.courses().byId(id)).build();
  }

  @GET
  @Path("professors/{id}")
  public Response getProfById(@PathParam("id") int id) {
    return Response.ok(data.profs().byId(id)).build();
  }

  @GET
  @Path("unis/{id}")
  public Response getUniById(@PathParam("id") int id) {
    return Response.ok(data.unis().byId(id)).build();
  }

  @GET
  @Path("courses")
  public Response getCourses(@Context UriInfo uriInfo) {
    return Response.ok(data.courses()
        .filter(Util.reflectionFilter(Course.class, uriInfo.getQueryParameters()))
        .collect(Collectors.toList())).build();
  }

  @GET
  @Path("professors")
  public Response getProfs(@Context UriInfo uriInfo) {
    return Response.ok(data.profs()
        .filter(Util.reflectionFilter(Professor.class, uriInfo.getQueryParameters()))
        .collect(Collectors.toList())).build();
  }

  @GET
  @Path("unis")
  public Response getUnis() {
    return Response.ok(data.unis().all()).build();
  }

  @GET
  @Path("professors/add")
  public Response addProfessor(@Context UriInfo uriInfo) {
    String name = uriInfo.getQueryParameters().getFirst("name");
    String uniId = uriInfo.getQueryParameters().getFirst("uniId");
    if (name == null || uniId == null) {
      throw new BadRequestException();
    }
    Professor p = new Professor(name, Identifiable.of(uniId));
    data.profs().put(p);
    return Response.ok(p).build();
  }

  @GET
  @Path("courses/add")
  public Response addCourse(@Context UriInfo uriInfo) {
    String name = uriInfo.getQueryParameters().getFirst("name");
    String semester = uriInfo.getQueryParameters().getFirst("semester");
    String credits = uriInfo.getQueryParameters().getFirst("credits");
    String description = uriInfo.getQueryParameters().getFirst("description");
    String profId = uriInfo.getQueryParameters().getFirst("profId");
    String uniId = uriInfo.getQueryParameters().getFirst("uniId");
    if (name == null || semester == null || credits == null || description == null || profId == null
        || uniId == null) {
      throw new BadRequestException();
    }
    Course c = new Course(name, Integer.valueOf(semester), Integer.valueOf(credits), description,
        Identifiable.of(uniId), Identifiable.of(profId));
    data.courses().put(c);
    return Response.ok(c).build();
  }

  @GET
  @Path("unis/add")
  public Response addUni(@Context UriInfo uriInfo){
    String shortName = uriInfo.getQueryParameters().getFirst("shortName");
    String name = uriInfo.getQueryParameters().getFirst("name");
    if (shortName == null || name == null) {
      throw new BadRequestException();
    }
    Uni u = new Uni(shortName, name);
    data.unis().put(u);
    return Response.ok(u).build();
  }
}
