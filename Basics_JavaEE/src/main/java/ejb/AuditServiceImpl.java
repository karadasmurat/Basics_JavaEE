package ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.LogRecord;
import entity.User;

@Stateless
public class AuditServiceImpl implements AuditService {
	
	private static final Logger LOGGER = Logger.getLogger(AuditServiceImpl.class.getName());

	@PersistenceContext(unitName = "Basics_JavaEE_PU")
	EntityManager em;
	
	public void log(String logText){
		
		User user = em.find(User.class, 1L);
		LogRecord logRecord = new LogRecord(user, logText);
		em.persist(logRecord);
	}

}
