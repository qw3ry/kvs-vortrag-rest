package elite.kvs.jaxrxdemo.repository;

import elite.kvs.jaxrxdemo.model.Course;
import elite.kvs.jaxrxdemo.model.Professor;
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
      UniRepository.Augsburg, ProfessorRepository.Reif);

  public static final Course FM = new Course("Formal Methods", 1, 6,
      "This course introduces the audience to techniques for the development of "
          + "provably correct software.The course starts with techniques for an exact "
          + "specification of the desired behavior and continues with a logic which can "
          + "prove the correspondence between implementation and specification.",
      UniRepository.Augsburg, ProfessorRepository.Knapp);

  public static final Course DB = new Course("Advanced Database Systems", 1, 6,
      "This course imparts knowledge on the design and operation of database "
          + "management systems. Starting with the relational data model and its foundation, "
          + "relational algebra, it continues with modeling and design techniques for "
          + "databases, query languages, database access from OO-languages like Java and "
          + "object-oriented databases. Furthermore it deals with transactions, multi-user "
          + "synchronization, error handling, etc.",
      UniRepository.TUM, ProfessorRepository.Kemper);

  public static final Course PM = new Course("Project-Management", 1, 6,
      "Project management is a key success factor for software development "
          + "projects. Challenges for the project management are increasing productivity, "
          + "ensuring quality, keeping the project within budget and timeframe. This course "
          + "introduces the functionsor project management, relevant processes and methods "
          + "and tools, from risk management to estimation methods.",
      UniRepository.LMU, ProfessorRepository.Wirsing);

  public static final Course KVS = new Course("Distributed Systems", 2, 6,
      "This course introduces fundamental concepts and methods for Distributed "
          + "Systems development. Key topics are communication (Fundamental Intercation "
          + "Model, Networks, Protocols and Services, Interprocess Communication), "
          + "Processes, Synchronization and Coordination (Processes and Threads, "
          + "Synchronization, Coordination) and Service-Oriented Architecture and Web "
          + "Services (SOA, Web Services, REST, Web Service Composition).",
      UniRepository.Augsburg, ProfessorRepository.Bauer);

  public static final Course HCI = new Course("Human-Computer-Interaction", 2, 6,
      "This course introduces new paradigms for the interaction between humans and "
          + "computers, the necessary theoretical background of modern interaction paradigms, "
          + "specific methods for identification and interpretation of user input and for the "
          + "generation and synchronization of system output. Another important topic are "
          + "guidelines for usability in the design of interactive systems.",
      UniRepository.Augsburg, ProfessorRepository.Andre);

  public static final Course Safety = new Course("Model-based Safety Analysis", 2, 6,
      "This course presents state-of-the-art techniques of safety analysis. Target "
          + "of these techniques is the analysis of highly security-critical systems and they "
          + "yield design guidelines to improve safety of these systems. Focus of this course "
          + "is the increasing importance of software in embedded systems. The lecture starts "
          + "with traditional safety analysis methods and then moves on to leading-edge "
          + "techniques which are based on Formal Methods.",
      UniRepository.Augsburg, ProfessorRepository.Reif);

  public static final Course MSV = new Course(
      "Modelling, Specification and Verification of Reactive Systems", 2, 6,
      "Distinct aspects of reactive systems are concurrency, communication and "
          + "non-termination. This course presents current approaches for modeling and "
          + "specification of reactive systems and introduces refinement and verification "
          + "techniques for reliable reactive systems.",
      UniRepository.Augsburg, ProfessorRepository.Knapp);

  public static final Course RE = new Course("Requirements Engineering", 2, 6,
      "Requirememts Engineering is a crucial part of every development project. "
          + "The gathered requirements are the basis of the development, integration and "
          + "approval of the new system. This lecture starts with an introduction of the "
          + "goals, responsibilities and topics of Requirements Engineering. It introduces "
          + "different kinds or requirements, crucial steps in the process, methods and "
          + "techniques of requirements gathering and the specification of the requirements. "
          + "Further topics are structured documentation of requirements, use case and "
          + "scenario modeling, non-functional requirements, requirements management and "
          + "system models in Requirements Engineering.",
      UniRepository.TUM, ProfessorRepository.Pretschner);

  public static final Course Testing = new Course("Testing", 2, 5,
      "Software Testing serves the evaluation and improvement of product quality "
          + "by identifying problems and deficiencies. The course introduces the fundamentals "
          + "of software testing, describes its position in the software development process "
          + "and discusses test methods and tools as well as methods for test management.",
      UniRepository.LMU, ProfessorRepository.Knapp);

  public static final Course MDD = new Course("Model-based Software Engineering", 2, 5,
      "The goal of model-based software development is to describe (as far as "
          + "possible) software systems through (semi-) formal models and to generate as many "
          + "artifacts as possible from those models. This lecture gives an overview of the "
          + "model-based software engineerin, focusing on the following main points: Modeling, "
          + "meta-modeling, transformation and code generation.",
      UniRepository.Augsburg, ProfessorRepository.Knapp);

  public static final Course DBImpl = new Course("Database Systems on Modern CPU Architectures", 2,
      6,
      "Implementation of Database Systems with respect to modern hardware: Storage "
          + "Layer, Access Paths, Transactions, Set-Oriented Query Processing, Algebraic "
          + "Operators. Understand the interaction between database systems resp. algorithms "
          + "and modern computer architecture (esp. CPU, Cache, Primary Storage) and learn how "
          + "to develop resp. to modify the internals of database systems in order to make use "
          + "of the properties of these computer architecture features.",
      UniRepository.TUM, ProfessorRepository.Neumann);

  public static final Course DS = new Course("Software Engineering for Distributed Systems", 3, 6,
      "Topic of this lecture are software architectures for distributed systems "
          + "(software architectures and organizations, software architecture design, "
          + "documentation of software architectures and evaluation of software architectures), "
          + "semantic technologies (semantic web, ontology languages for the semantic web, "
          + "description logics, reasoning with OWL and description logic reasoning) and "
          + "multi-agent systems(intelligent agents, agent architectures, reactive and hybrid "
          + "agents, approaches for decision finding, interaction in multi-agent systems and "
          + "negotiation and coordination and cooperation through communication).",
      UniRepository.Augsburg, ProfessorRepository.Bauer);
  public static final Course ES = new Course("Software Engineering for Embedded Systems", 3, 6,
      "Embedded systems are systems that are designed, integrated and operated in a "
          + "technical environment for a specific purpose. Examples for fields of application "
          + "of embedded systems are automobiles, airplanes, home appliances, mobile "
          + "communication and consumer electronics. This lecture gives an overview on the "
          + "design, the implementation and the technical environment of embedded systems.",
      UniRepository.Augsburg, ProfessorRepository.Knapp);

  public static final Course ITSec = new Course("Practical IT-Security", 3, 3,
      "This course introduces fundamental concepts, techniques, methods and "
          + "applications for IT security. Among other topics, the lecture covers threats "
          + "and attacks on IT systems, goals of IT security, like integrity, secrecy and "
          + "availability, cryptography, access control, authentication, network security "
          + "and security engineering.",
      UniRepository.LMU, ProfessorRepository.Wirsing);

  public static final Course UX = new Course("Usability Engineering", 3, 6,
      "Because of the increasing pervasiveness of computers in all aspects of "
          + "life and their embedding in the user's natural environment, the shaping of "
          + "the human-computer interaction is an increasingly important challenge. This "
          + "lecture presents design principles and standards, the human information-, "
          + "activity- and perception-processes, a user-centered development process and "
          + "methods for the evaluation of interactive systems.",
      UniRepository.Augsburg, ProfessorRepository.Andre);

  public static final Course WebDB = new Course("Web-Databases", 3, 5,
      "In many cases, databases are accessible to the user via web-based interfaces. "
          + "Examples are online shopping and web banking. The large number of users and the "
          + "distribution of the application on different hard- and software systems like web "
          + "servers, database servers, application servers, etc. led to the development of "
          + "special software architectures and development paradigms. This lecture presents "
          + "current programming languages, technologies and architectures for modern "
          + "web-based information systems.",
      UniRepository.TUM, ProfessorRepository.Kemper);

  public CourseRepository() {
    super(SE, FM, DB, PM, KVS, HCI, Safety, MSV, RE, Testing, MDD, DBImpl, DS, ES, ITSec, UX,
        WebDB);
  }
}
