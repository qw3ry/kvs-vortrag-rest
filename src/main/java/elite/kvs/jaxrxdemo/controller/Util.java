package elite.kvs.jaxrxdemo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import elite.kvs.jaxrxdemo.model.Identifiable;
import elite.kvs.jaxrxdemo.model.LinkedObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

class Util {

  /**
   * Filter any object by its member values
   *
   * @param clazz the class type to filter
   * @param filter the map of the values to check for equality
   * @param <T> the type that should be filtered
   * @return a predicate that can be used for filtering
   */
  static <T> Predicate<T> reflectionFilter(Class<T> clazz, MultivaluedMap<String, String> filter) {
    return t -> Arrays.stream(clazz.getFields()).filter(f -> filter.containsKey(f.getName()))
        .noneMatch(field -> {
          try {
            return !filter.containsKey(field.getName()) ||
                !field.get(t).toString().equals(filter.getFirst(field.getName()));
          } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
          }
        });
  }

  /**
   * Reduce the object to certain fields for serialization
   *
   * @param o the object to reduce
   * @param fields the fields to filter
   * @return the map containing only the fields that are filtered for
   */
  static Map<String, Object> filterFields(Object o, Collection<String> fields) {
    return Fields.filterFields(o, fields);
  }

  /**
   * prepare a collection for serialization
   *
   * @param coll the collection
   * @param apiVersion the version of the api used
   * @param info the uri info object
   * @param <T> the type of the collection elements
   * @return a fully linked and paginated collection
   */
  static <T extends Identifiable> LinkedObject<Collection<LinkedObject<Map<String, Object>>>> prepareLinkedCollection(
      Collection<T> coll, String apiVersion, UriInfo info) {
    Pagination pag = new Pagination(info, coll.size());
    return Links.linkCollection(pag.paginate(coll.stream()), apiVersion, '/' + info.getPath(), pag,
        coll.size(), o -> filterFields(o, info.getQueryParameters().get("fields")));
  }

  /**
   * prepare a collection for serialization
   *
   * @param collection the collection
   * @param info the uri info object
   * @param <T> the type of the collection elements
   * @return a paginated collection (no link-wrappers)
   */
  static <T> Collection<Map<String, Object>> prepareCollection(Collection<T> collection,
      UriInfo info) {
    return prepareCollection(collection, info, (s, p) -> s.collect(Collectors.toList()));
  }

  private static <T, V> V prepareCollection(Collection<T> collection, UriInfo info,
      BiFunction<Stream<Map<String, Object>>, Pagination, V> mapping) {
    Pagination pag = new Pagination(info, collection.size());
    Stream<Map<String, Object>> stream = collection.stream()
        .map(o -> filterFields(o, info.getQueryParameters().get("fields")));
    return mapping.apply(pag.paginate(stream), pag);
  }

  static <T> Predicate<T> paramEquals(MultivaluedMap<String, String> map, String param,
      Function<T, ?> mapOther) {
    String obj = map.getFirst(param);
    return obj == null ? null : other -> obj.equals(mapOther.apply(other).toString());
  }
}

class Fields {

  static Map<String, Object> filterFields(Object o, Collection<String> fields) {
    ObjectMapper m = new ObjectMapper();
    Map<String, Object> map = m.convertValue(o, new TypeReference<Map<String, Object>>() {
    });
    return fields == null ? map : filterFieldsInMap(map, fields);
  }

  private static <T> Map<String, T> filterFieldsInMap(Map<String, T> map,
      Collection<String> fields) {
    return map.keySet().stream().filter(fields::contains)
        .collect(Collectors.toMap(s -> s, map::get));
  }
}

class Pagination {

  int page;
  int size;

  Pagination(UriInfo info, int defaultSize) {
    this.page = getOrDefault(info.getQueryParameters(), "page", 0);
    this.size = getOrDefault(info.getQueryParameters(), "size", defaultSize);
  }

  <T> Stream<T> paginate(Stream<T> stream) {
    return stream.skip(page * size).limit(size);
  }

  private static int getOrDefault(MultivaluedMap<String, String> params, String key, int def) {
    return params.containsKey(key) ? Integer.valueOf(params.getFirst(key)) : def;
  }
}

class Links {

  static <T extends Identifiable, U> LinkedObject<Collection<LinkedObject<U>>> linkCollection(
      Stream<T> items, String apiVersion, String url, Pagination pag, int totalSize,
      Function<T, U> itemMapping) {
    Collection<LinkedObject<U>> c = linkItems(items, apiVersion, itemMapping)
        .collect(Collectors.toList());
    return linkCollection(c, url, pag, totalSize);
  }

  private static <T> LinkedObject<Collection<T>> linkCollection(Collection<T> collection,
      String url, Pagination pag, int totalSize) {
    return new LinkedObject<>(collection, getLinks(url, pag, totalSize));
  }

  private static Link collectionLink(String url, String rel, int page, int size) {
    UriBuilder uri = UriBuilder.fromUri(url).queryParam("page", page).queryParam("size", size);
    return Link.fromUriBuilder(uri).rel(rel).build();
  }

  private static Link[] getLinks(String url, Pagination pag, int totalSize) {
    int lastPage = (totalSize - 1) / pag.size;
    List<Link> links = new ArrayList<>(5);
    links.add(collectionLink(url, "self", pag.page, pag.size));
    links.add(collectionLink(url, "first", 0, pag.size));
    links.add(collectionLink(url, "last", lastPage, pag.size));
    if (pag.page > 0) {
      links.add(collectionLink(url, "prev", pag.page - 1, pag.size));
    }
    if (pag.page < lastPage) {
      links.add(collectionLink(url, "next", pag.page + 1, pag.size));
    }
    return links.toArray(new Link[0]);
  }

  private static <T extends Identifiable, U> Stream<LinkedObject<U>> linkItems(Stream<T> stream,
      String apiVersion, Function<T, U> mapping) {
    return stream.map(t -> new LinkedObject<>(mapping.apply(t), t.createLink(apiVersion, "self")));
  }
}
