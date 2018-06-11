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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
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
  public Response getCourses(
      @QueryParam("name") String name,
      @QueryParam("semester") Integer semester,
      @QueryParam("credits") Integer credits,
      @QueryParam("uni") Integer uniId,
      @QueryParam("prof") Integer profId) {
    return Response.ok(data.courses().filter(
        name == null ? null : c -> name.equals(c.name),
        semester == null ? null : c -> semester.equals(c.semester),
        credits == null ? null : c -> credits.equals(c.credits),
        uniId == null ? null : c -> uniId.equals(c.uniId),
        profId == null ? null : c -> profId.equals(c.profId)
    ).collect(Collectors.toList())).build();
  }

  @GET
  @Path("professors")
  public Response getProfs(
      @QueryParam("name") String name,
      @QueryParam("uni") Integer uni
  ) {
    return Response.ok(data.profs().filter(
        name == null ? null : p -> name.equals(p.name),
        uni == null ? null : p -> uni.equals(p.uniId)
    ).collect(Collectors.toList())).build();
  }

  @GET
  @Path("unis")
  public Response getUnis() {
    return Response.ok(data.unis().all()).build();
  }

  @GET
  @Path("professors/add")
  public Response addProfessor(@QueryParam("name") String name, @QueryParam("uni") Integer uniId) {
    if (name == null || uniId == null) {
      throw new BadRequestException();
    }
    Professor p = new Professor(name, Identifiable.of(uniId));
    data.profs().put(p);
    return Response.ok(p).build();
  }

  @GET
  @Path("courses/add")
  public Response addCourse(
      @QueryParam("name") String name,
      @QueryParam("semester") Integer semester,
      @QueryParam("credits") Integer credits,
      @QueryParam("desc") String description,
      @QueryParam("prof") Integer profId,
      @QueryParam("uni") Integer uniId) {
    if (name == null || semester == null || credits == null || description == null || profId == null
        || uniId == null) {
      throw new BadRequestException();
    }
    Course c = new Course(name, semester, credits, description, Identifiable.of(uniId),
        Identifiable.of(profId));
    data.courses().put(c);
    return Response.ok(c).build();
  }

  @GET
  @Path("unis/add")
  public Response addUni(
      @QueryParam("shortName") String shortName,
      @QueryParam("name") String name) {
    if (shortName == null || name == null) {
      throw new BadRequestException();
    }
    Uni u = new Uni(shortName, name);
    data.unis().put(u);
    return Response.ok(u).build();
  }
}
