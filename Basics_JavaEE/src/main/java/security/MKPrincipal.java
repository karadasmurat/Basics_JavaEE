package security;

import org.jboss.security.SimplePrincipal;

import entity.User;

public class MKPrincipal extends SimplePrincipal {
	
    private String description;
    private User user;

    public MKPrincipal(String name, String description) {
        super(name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
