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

import ejb.ResourceService;
import ejb.UserService;
import entity.contactinfo.City;
import entity.contactinfo.PhoneSubType;
import entity.contactinfo.PhoneType;

@Named
@SessionScoped
public class PhoneTypeConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(PhoneTypeConverter.class.getName());
	
	@EJB
	private ResourceService resourceService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

		// Called when HTTP request parameter is to be converted to item value.

		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		Long pid = Long.valueOf(submittedValue);

		return resourceService.findPhoneType(pid);

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {

		if (modelValue == null) {
			return "";
		}

		return String.valueOf(((PhoneType) modelValue).getId());
	}

}
