package ejb;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import entity.User;

@Stateless
public class UserServiceImpl implements UserService {

	private Configuration configuration;
	private SessionFactory sessionFactory;

	public UserServiceImpl() {
		configuration = (new Configuration()).configure();
		StandardServiceRegistryBuilder builder = (new StandardServiceRegistryBuilder()).applySettings(configuration
				.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
	}

	@Override
	public void persistUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
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
