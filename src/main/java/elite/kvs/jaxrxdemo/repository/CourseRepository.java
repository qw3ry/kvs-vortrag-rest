package elite.kvs.jaxrxdemo.repository;

import elite.kvs.jaxrxdemo.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository extends CollectionRepository<Course> {

  public static final Course SE = new Course("Software Engineering", 1, 6,
      "This course offers an introduction to current methods in systematic "
          + "software development. Starting with the software life-cycle, the course "
          + "deals with the different phases of software development and the currently "
          + "relevant software development proesses. The main focus is the Unified Process "
          + "and the usage of the UML in the different phases of development. Further "
          + "topcis are testing, design patterns and GUI design.",
      UniRepository.Augsburg,
      ProfessorRepository.Reif);
  public static final Course FM = new Course("Formal Methods", 1, 6,
      "This course introduces the audience to techniques for the development of "
          + "provably correct software.The course starts with techniques for an exact "
          + "specification of the desired behavior and continues with a logic which can "
          + "prove the correspondence between implementation and specification.",
      UniRepository.Augsburg,
      ProfessorRepository.Knapp);
  public static final Course DB = new Course("Advanced Database Systems", 1, 6,
      "This course imparts knowledge on the design and operation of database "
          + "management systems. Starting with the relational data model and its foundation, "
          + "relational algebra, it continues with modeling and design techniques for "
          + "databases, query languages, database access from OO-languages like Java and "
          + "object-oriented databases. Furthermore it deals with transactions, multi-user "
          + "synchronization, error handling, etc.",
      UniRepository.TUM,
      ProfessorRepository.Kemper);
  public static final Course PM = new Course("Project-Management", 1, 6,
      "Project management is a key success factor for software development "
          + "projects. Challenges for the project management are increasing productivity, "
          + "ensuring quality, keeping the project within budget and timeframe. This course "
          + "introduces the functionsor project management, relevant processes and methods "
          + "and tools, from risk management to estimation methods.",
      UniRepository.LMU,
      ProfessorRepository.Wirsing);
  public static final Course DistSys = new Course("Distributed Systems", 2, 6,
      "This course introduces fundamental concepts and methods for Distributed "
          + "Systems development. Key topics are communication (Fundamental Intercation "
          + "Model, Networks, Protocols and Services, Interprocess Communication), "
          + "Processes, Synchronization and Coordination (Processes and Threads, "
          + "Synchronization, Coordination) and Service-Oriented Architecture and Web "
          + "Services (SOA, Web Services, REST, Web Service Composition).",
      UniRepository.Augsburg,
      ProfessorRepository.Bauer);
  // TODO moar

  public CourseRepository() {
    super(SE, FM, DB, PM, DistSys);
  }
}
