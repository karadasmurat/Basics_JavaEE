package ejb;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import entity.PhoneSubType;
import entity.PhoneType;
import entity.Role;
import entity.RoleSet;
import entity.User;

@Local
public interface UserService {

	public abstract void persistUser(User user);

	public abstract String createHash(String s);
	
	public User getUserByName(String username);
	
	public List<Role> getAllRoles();
	
	public Role findRoleByRolename(String arg);
	public RoleSet findRoleSetByRolename(String arg);
	
	public String logout();

	List<PhoneType> findPhoneTypes();
	PhoneType findPhoneTypeById(long arg);
	PhoneType findPhoneTypeByTitle(String arg);

	PhoneSubType findPhoneSubTypeById(long arg);

	List<PhoneSubType> findPhoneSubTypes();

	PhoneSubType findPhoneSubTypeByTitle(String arg);

}
