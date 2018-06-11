package elite.kvs.jaxrxdemo.model;

import javax.ws.rs.core.Link;

public class Uni extends Identifiable {

  public String name;
  public String shortName;

  public Uni(String shortName, String name) {
    super();
    this.name = name;
    this.shortName = shortName;
  }

  private Uni() {
  }

  @Override
  public Link createLink(String apiVersion, String rel) {
    return Link.fromPath("/{v}/unis/{id}").rel(rel).build(apiVersion, id);
  }
}
