package elite.kvs.jaxrxdemo.repository;

import elite.kvs.jaxrxdemo.model.Uni;
import org.springframework.stereotype.Repository;

@Repository
public class UniRepository extends CollectionRepository<Uni> {

  public static final Uni Augsburg = new Uni("UNIA", "Universität Augsburg");
  public static final Uni TUM      = new Uni("TUM", "Technische Universität München");
  public static final Uni LMU      = new Uni("LMU", "Ludwig-Maximilians-Universität München");

  public UniRepository() {
    super();
    super.put(Augsburg);
    super.put(TUM);
    super.put(LMU);

  }
}
