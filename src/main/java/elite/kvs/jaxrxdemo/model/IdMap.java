package elite.kvs.jaxrxdemo.model;

import java.util.Arrays;
import java.util.HashMap;

public class IdMap<T extends Identifiable> extends HashMap<Integer, T> {
  @SafeVarargs
  public IdMap(T... args) {
    Arrays.asList(args).forEach(this::put);
  }

  public T put(T t) {
    return put(t.id, t);
  }
}
