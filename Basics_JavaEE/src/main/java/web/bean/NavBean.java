package web.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.jboss.security.SimplePrincipal;

import security.MKPrincipal;

import com.sun.security.auth.UserPrincipal;

import ejb.UserService;

@Named
@SessionScoped
public class NavBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	UserService userService;
	
	UserPrincipal userPrincipal;
	SimplePrincipal simplePrincipal;
	MKPrincipal mkPrincipal;
	
	public NavBean(){
		
		mkPrincipal = (MKPrincipal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
	}

	public UserPrincipal getUserPrincipal() {
		return userPrincipal;
	}

	public void setUserPrincipal(UserPrincipal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}	
	
	
	public SimplePrincipal getSimplePrincipal() {
		return simplePrincipal;
	}

	public void setSimplePrincipal(SimplePrincipal simplePrincipal) {
		this.simplePrincipal = simplePrincipal;
	}	

	public MKPrincipal getMkPrincipal() {
		return mkPrincipal;
	}

	public void setMkPrincipal(MKPrincipal mkPrincipal) {
		this.mkPrincipal = mkPrincipal;
	}

	public String logout(){
		
		userService.logout();
		
		/*
		try {
			ExternalContext eCtx = FacesContext.getCurrentInstance().getExternalContext();
			eCtx.redirect(eCtx.getRequestContextPath()+"/login.html");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		return "MKLogin";
		
	}
	

}
