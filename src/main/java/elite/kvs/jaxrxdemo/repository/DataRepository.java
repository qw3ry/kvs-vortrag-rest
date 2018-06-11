package elite.kvs.jaxrxdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {

  private final CourseRepository courses;
  private final ProfessorRepository profs;
  private final UniRepository unis;

  @Autowired
  public DataRepository(CourseRepository courses, ProfessorRepository profs, UniRepository unis) {
    this.courses = courses;
    this.profs = profs;
    this.unis = unis;
  }

  public CourseRepository courses() {
    return courses;
  }

  public ProfessorRepository profs() {
    return profs;
  }

  public UniRepository unis() {
    return unis;
  }
}
