package elite.kvs.jaxrxdemo.model;

import javax.ws.rs.core.Link;

public class Identifiable {

  private static int next_id = 0;

  public final int id;

  public Identifiable() {
    this.id = next_id++;
  }

  private Identifiable(int id) {
    this.id = id;
  }

  public Link createLink(String apiVersion, String rel) {
    return null;
  }

  public static Identifiable of(int id) {
    return new Identifiable(id);
  }

  public static Identifiable of(String id) {
    return new Identifiable(Integer.valueOf(id));
  }
}
