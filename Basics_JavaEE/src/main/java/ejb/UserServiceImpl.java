package ejb;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entity.PhoneSubType;
import entity.PhoneType;
import entity.Role;
import entity.RoleSet;
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
	
	
	public List<Role> getAllRoles() {

		List<Role> roleList = new ArrayList<Role>(0);
		
		Query query = em.createNamedQuery("RoleSet.findAll");

		List<RoleSet> roleset = query.getResultList();		
		
		for(RoleSet rs : roleset){
			roleList.add(rs.getAsRole());
		}

		return roleList;
	}
	
	public Role findRoleByRolename(String arg) {
		
		Role result= (Role) em.createNamedQuery("Role.findByRolename").setParameter("rname", arg).getSingleResult();
		return result;
	}
	
	public RoleSet findRoleSetByRolename(String arg) {
		
		LOGGER.log(Level.FINE, "MK: Searching RoleSet [RoleSet.findByRolename]: " + arg);
		
		RoleSet result= (RoleSet) em.createNamedQuery("RoleSet.findByRolename").setParameter("rname", arg).getSingleResult();
		
		if(result == null){
			LOGGER.log(Level.FINE, "MK: Done searching RoleSet [RoleSet.findByRolename]. No results found. " );
		}else{
			LOGGER.log(Level.FINE, "MK: Done searching RoleSet [RoleSet.findByRolename]. Found: " + result.getRole() );
		}
		
		return result;
	}
	
	@Override
	public PhoneType findPhoneTypeById(long arg) {
		
		return em.find(PhoneType.class, arg);

	}
	
	@Override
	public List<PhoneType> findPhoneTypes() {

		Query query = em.createNamedQuery("PhoneType.findAll");

		List<PhoneType> phoneTypes = query.getResultList();
		
		LOGGER.log(Level.FINE, "MK: Done searching PhoneType data. ");		
		return phoneTypes;
	}
	
	@Override
	public PhoneType findPhoneTypeByTitle(String arg) {
		
		PhoneType result= (PhoneType) em.createNamedQuery("PhoneType.findByTitle").setParameter("title", arg).getSingleResult();
		
		if(result == null){
			LOGGER.log(Level.FINE, "MK: Done searching PhoneType [findPhoneTypeByTitle]. No results found. " );
		}else{
			LOGGER.log(Level.FINE, "MK: Done searching PhoneType [findPhoneTypeByTitle]. Found: " + result.getTitle() );
		}
		
		return result;
	}
	
	
	@Override
	public PhoneSubType findPhoneSubTypeById(long arg) {
		
		return em.find(PhoneSubType.class, arg);

	}
	
	@Override
	public List<PhoneSubType> findPhoneSubTypes() {

		Query query = em.createNamedQuery("PhoneSubType.findAll");

		List<PhoneSubType> phoneSubTypes = query.getResultList();
		
		LOGGER.log(Level.FINE, "MK: Done searching PhoneSubType data. ");		
		return phoneSubTypes;
	}
	
	@Override
	public PhoneSubType findPhoneSubTypeByTitle(String arg) {
		
		PhoneSubType result= (PhoneSubType) em.createNamedQuery("PhoneSubType.findByTitle").setParameter("title", arg).getSingleResult();
		
		if(result == null){
			LOGGER.log(Level.FINE, "MK: Done searching PhoneSubType [findPhoneSubTypeByTitle]. No results found. " );
		}else{
			LOGGER.log(Level.FINE, "MK: Done searching PhoneSubType [findPhoneSubTypeByTitle]. Found: " + result.getTitle() );
		}
		
		return result;
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
	
	
	public String logout() {   
		
		LOGGER.log(Level.FINE, "MK: logout() ");
		
	    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	    
	    HttpServletRequest request = (HttpServletRequest) context.getRequest();
	    
	    LOGGER.log(Level.FINE, "MK: Calling request.logout() ");
	    try {
			request.logout();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    Object sessionObject = context.getSession(false);
	    
	    if (!(sessionObject instanceof HttpSession)) {
	        //logger.error("request object wrong type.");	        
	        return "error";
	    }
	    
	    LOGGER.log(Level.FINE, "MK: Invalidating session ");
	    HttpSession session = (HttpSession) sessionObject;
	    session.invalidate();
	    
	    return "login";
	}

}
