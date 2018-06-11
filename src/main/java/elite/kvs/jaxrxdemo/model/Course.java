package elite.kvs.jaxrxdemo.model;

import javax.ws.rs.core.Link;

public class Course extends Identifiable {

  public String name;
  public int semester;
  public int credits;
  public String description;
  public int uniId;
  public int profId;

  public Course(String name, int semester, int credits, String description, Identifiable uni, Identifiable prof) {
    super();
    this.name = name;
    this.semester = semester;
    this.credits = credits;
    this.description = description;
    this.uniId = uni.id;
    this.profId = prof.id;
  }

  private Course() {}

  @Override
  public Link createLink(String apiVersion, String rel) {
    return Link.fromPath("/{v}/courses/{id}").rel(rel).build(apiVersion, id);
  }
}
