package web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ejb.MailService;
import ejb.UserService;
import entity.Role;
import entity.Status;
import entity.User;

@Named
@RequestScoped
public class UserBean {

	private static final Logger LOGGER = Logger.getLogger(UserBean.class.getName());

	@EJB
	protected UserService userService;

	@EJB
	protected MailService mailService;

	private User user;
	protected String status;
	private Future mailStatus;
	
    private List<Role> allRoles;
    private List<Role> selectedRoles;
    
	public UserBean() {
		
		LOGGER.log(Level.INFO, "Initializing UserBean");
		
		user = new User();
		user.setStatus(Status.ACTIVE);
		
	    allRoles = new ArrayList<Role>(0);
	    selectedRoles = new ArrayList<Role>(0);	
		
		
		/*
		Role role = new Role();
		role.setRole("todo_role");
		role.setRoleGroup("Roles");
		
		user.addRole(role);
		*/
	}

	@PostConstruct
	public void initialize() {
		
		allRoles = userService.getAllRoles();
	}

	public String createUser() {
		
		LOGGER.log(Level.INFO, "createUser()");
		user.setHashedPassword(userService.createHash(user.getRawPassword()));
		
		//arrangeRelations();
		
		persist();
		
		LOGGER.log(Level.FINE, "MK: Sending user creation mail.");
		sendMail("murat.karadas@avea.com.tr");
		
		return "response";
	}

	public void persist() {
		
		LOGGER.log(Level.FINE, "MK: Persisting user.");		
		
		userService.persistUser(user);
	}
	
	private void arrangeRelations(){
		
		for(Role r : selectedRoles){
			this.user.addRole(r);
		}
	}

	public String sendMail(String recipient_TO) {
		LOGGER.log(Level.FINE, "sendMail()");
		String response = "response?faces-redirect=true";
		try {
			mailStatus = mailService.sendMessage(recipient_TO);
			setStatus("Processing... (refresh to check again)");
		} catch (Exception ex) {
			LOGGER.severe(ex.getMessage());
		}
		return response;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public List<Role> getSelectedRoles() {
		
		arrangeRelations();
		return selectedRoles;
	}

	public void setSelectedRoles(List<Role> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}
	
	
	
	

}
