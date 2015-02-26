package ejb;

import javax.ejb.Local;

import org.hibernate.Session;

import entity.Todo;
import entity.User;

@Local
public interface UserService {

	public abstract void persistUser(User user);

	public abstract String createHash(String s);
	
	public User getUserByName(String username);

}
