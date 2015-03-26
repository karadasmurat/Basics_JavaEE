package converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import ejb.UserService;
import entity.Role;
import entity.RoleSet;

@ManagedBean
@RequestScoped
public class RoleConverter implements Converter {
	
	private static final Logger LOGGER = Logger.getLogger(RoleConverter.class.getName());

	@EJB
	private UserService userService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			
			LOGGER.log(Level.FINE, "MK: Converting submittedValue to Role, with role=" +submittedValue);
		
			//RoleSet rs = (RoleSet)userService.findRoleSetByRolename(submittedValue);
			//if(rs == null){
			//	LOGGER.log(Level.FINE, "MK: Could not find RoleSet for conversion, with role=" + submittedValue );
			//}else{
			//	LOGGER.log(Level.FINE, "MK: Ready to convert: " + rs.getRole());
			//}
			
			//return rs.getAsRole();
			
			
			Role role = new Role();
			role.setRole(submittedValue);			
			return role;	
	
			
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid Role Name", submittedValue)),
					e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		
		if (modelValue == null) {
			return "";
		}

		//if (modelValue instanceof Role) {
			
			LOGGER.log(Level.FINE, "MK: Converting modelValue to String, with role=" +((Role)modelValue).getRole());
			return String.valueOf( ((Role) modelValue).getRole() );
			
		//} else {
		//	throw new ConverterException(new FacesMessage(String.format("%s is not a valid User", modelValue)));
		//}
	}

}
