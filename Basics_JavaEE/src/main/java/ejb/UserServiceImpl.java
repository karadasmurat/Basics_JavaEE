package ejb;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import entity.User;

@Stateless
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

	@PersistenceContext(unitName = "Basics_JavaEE_PU")
	EntityManager em;

	public UserServiceImpl() {		
		LOGGER.log(Level.FINE, "MK: Constructing UserServiceImpl ");
	}

	@Override
	public void persistUser(User user) {		
		em.persist(user);
	}
	
	@Override
	public User getUserByName(String username) {
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		
//		Criteria criteria = session.createCriteria(User.class);
//		criteria.add(Restrictions.eq("username", username));
//		List<User> results = criteria.list();
//				
//		//User user = (User) session.get(User.class, username);
//		session.getTransaction().commit();
//		session.close();
		return new User();
	}
	
	
	

	@Override
	public String createHash(String arg) {

		// online hash generator https://quickhash.com/
		String hashedArg;
		java.util.Base64.Encoder encoder;
		hashedArg = null;
		encoder = Base64.getEncoder();
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			sha.update(arg.getBytes("UTF-8"));
			byte digest[] = sha.digest();
			hashedArg = encoder.encodeToString(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashedArg;
	}

}
