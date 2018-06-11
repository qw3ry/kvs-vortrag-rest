package elite.kvs.jaxrxdemo.repository;

import elite.kvs.jaxrxdemo.model.IdMap;
import elite.kvs.jaxrxdemo.model.Identifiable;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.NotFoundException;

public abstract class CollectionRepository<T extends Identifiable> {

  private final IdMap<T> map;

  @SafeVarargs
  CollectionRepository(T... ts) {
    map = new IdMap<>(ts);
  }

  public Collection<T> all() {
    return map.values();
  }

  public T byId(int id) {
    if (map.containsKey(id)) {
      return map.get(id);
    } else {
      throw new NotFoundException();
    }
  }

  public T delete(int id) {
    if (map.containsKey(id)) {
      return map.remove(id);
    } else {
      throw new NotFoundException();
    }
  }

  public T put(T t) {
    return map.put(t);
  }

  @SafeVarargs
  public final Stream<T> filter(Predicate<T>... ps) {
    Stream<T> stream = map.values().stream();
    for (Predicate<T> p : ps) {
      if (p != null) {
        stream = stream.filter(p);
      }
    }
    return stream;
  }
}
