package elite.kvs.jaxrxdemo.model;

import javax.ws.rs.core.Link;

public class Professor extends Identifiable {

  public String name;
  public int uniId;

  public Professor(String name, Identifiable uni) {
    super();
    this.name = name;
    this.uniId = uni.id;
  }

  private Professor() {}

  @Override
  public Link createLink(String apiVersion, String rel) {
    return Link.fromPath("/{v}/professors/{id}").rel(rel).build(apiVersion, id);
  }
}
