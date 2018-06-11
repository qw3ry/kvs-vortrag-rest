package elite.kvs.jaxrxdemo.repository;

import elite.kvs.jaxrxdemo.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfessorRepository extends CollectionRepository<Professor> {

  public static final Professor Knapp = new Professor("Alexander Knapp", UniRepository.Augsburg);
  public static final Professor Reif = new Professor("Wolfgang Reif", UniRepository.Augsburg);
  public static final Professor Bauer = new Professor("Bernhard Bauer", UniRepository.Augsburg);
  public static final Professor Kemper = new Professor("Alfons Kemper", UniRepository.TUM);
  public static final Professor Wirsing = new Professor("Martin Wirsing", UniRepository.LMU);

  @Autowired
  public ProfessorRepository(UniRepository uni) {
    super(Knapp);
  }
}
