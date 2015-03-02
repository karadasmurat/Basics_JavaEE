package ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Certificate;
import entity.Employee;

@Stateless
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = Logger
			.getLogger(EmployeeServiceImpl.class.getName());

	@PersistenceContext(unitName = "Basics_JavaEE_PU")
	EntityManager em;
	
	@EJB
	AuditService auditService;

	// private Configuration config;
	// private SessionFactory sessionFactory;

	public EmployeeServiceImpl() {

		// LOGGER.log(Level.INFO, "EmployeeServiceImpl with EntityManager: " +
		// em.toString());

		/*
		 * config = (new Configuration()).configure();
		 * StandardServiceRegistryBuilder builder = (new
		 * StandardServiceRegistryBuilder())
		 * .applySettings(config.getProperties()); sessionFactory =
		 * config.buildSessionFactory(builder.build()); LOGGER.log(Level.INFO,
		 * "EmployeeServiceImpl.SESSIONFACTORY");
		 */

	}

	@Override
	public void persistEmployee(Employee employee) {

		LOGGER.log(Level.INFO, "persistEmployee()");

		/*
		 * Session session = sessionFactory.openSession();
		 * session.beginTransaction(); session.save(employee);
		 * session.getTransaction().commit(); session.close();
		 */

		em.persist(employee);
		auditService.log("Done persisting employee: "+employee.getId());
	}

	public void assignCertificateToEmployee(Certificate cert, Employee emp) {

		//bidirectional relationship
		emp.getCertificates().add(cert);
		cert.getEmployees().add(emp);
	}

}
