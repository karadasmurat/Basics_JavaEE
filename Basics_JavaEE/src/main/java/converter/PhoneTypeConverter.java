package converter;

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import ejb.UserService;
import entity.PhoneSubType;
import entity.PhoneType;

@Named
@SessionScoped
public class PhoneTypeConverter implements Converter, Serializable {
	
	@EJB
	private UserService userService;
	
	private static final Logger LOGGER = Logger.getLogger(PhoneTypeConverter.class.getName());

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		
		LOGGER.log(Level.FINE, "MK: Converting submittedValue to PhoneType: " + submittedValue);
		
		PhoneType pt = userService.findPhoneTypeByTitle(submittedValue);
		
		LOGGER.log(Level.FINE, "MK: PhoneType Title: " + pt.getTitle());
		Set<PhoneSubType> psts = pt.getPhoneSubTypes();
		LOGGER.log(Level.FINE, "MK: PhoneType SubTypes of " + pt.getTitle() + ": ");
		for(PhoneSubType var : psts){
			LOGGER.log(Level.FINE, "MK: SubType: " + var.getTitle());
		}
		
		LOGGER.log(Level.FINE, "MK: Converting submittedValue to PhoneType: " + submittedValue);
		
		return pt;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		
		if (modelValue == null) {
			return "";
		}

		String title = ((PhoneType)modelValue).getTitle();
		LOGGER.log(Level.FINE, "MK: Converting modelValue to String: " + title);
		
		
		return title;
		
	}

}
