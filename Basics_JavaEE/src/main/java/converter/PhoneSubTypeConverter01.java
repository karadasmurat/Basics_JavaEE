package converter;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import ejb.UserService;
import entity.contactinfo.PhoneSubType;

@Named
@SessionScoped
public class PhoneSubTypeConverter01 implements Converter, Serializable {
	
	@EJB
	private UserService userService;
	
	private static final Logger LOGGER = Logger.getLogger(PhoneSubTypeConverter01.class.getName());

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		LOGGER.log(Level.FINE, "MK: Converting submittedValue to PhoneSubType: " + submittedValue);
		
		return userService.findPhoneSubTypeByTitle(submittedValue);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		
		if (modelValue == null) {
			return "";
		}

		String title = ((PhoneSubType)modelValue).getTitle();
		LOGGER.log(Level.FINE, "MK: Converting modelValue to String: " + title);
		
		
		return title;
		
	}

}
