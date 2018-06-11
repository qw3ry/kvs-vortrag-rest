package elite.kvs.jaxrxdemo.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.ws.rs.core.Link;

public class LinkedObject<T> {
  private final T data;
  private final Collection<Link> links;

  public class HateoasLink {
    public final String rel;
    public final String href;
    public HateoasLink(Link l) {
      this.rel = l.getRel();
      this.href = l.getUri().toString();
    }
  }

  public LinkedObject(T t, Link... links) {
    this.data = t;
    this.links = Arrays.asList(links);
  }

  public T getData() {
    return data;
  }

  public Collection<HateoasLink> getLinks() {
    return links.stream().map(HateoasLink::new).collect(Collectors.toSet());
  }
}
