package web.bean;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ejb.MailService;
import ejb.UserService;
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

	public UserBean() {
		LOGGER.log(Level.INFO, "Initializing UserBean");
		user = new User();
	}

	@PostConstruct
	public void initialize() {
	}

	public String createUser() {
		LOGGER.log(Level.INFO, "createUser()");
		persist();
		return "response";
	}

	public void persist() {
		LOGGER.log(Level.FINE, "persist()");
		user.setHashedPassword(userService.createHash(user.getRawPassword()));
		userService.persistUser(user);
		sendMail("murat.karadas@avea.com.tr");
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

}
