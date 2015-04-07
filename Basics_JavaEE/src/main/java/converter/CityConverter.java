package converter;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ejb.ResourceService;
import entity.contactinfo.City;

@ManagedBean
@RequestScoped
public class CityConverter implements Converter {

	private static final Logger LOGGER = Logger.getLogger(CityConverter.class.getName());

	@EJB
	private ResourceService resourceService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

		// Called when HTTP request parameter is to be converted to item value.

		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		Long cid = Long.valueOf(submittedValue);
		return resourceService.findCity(cid);

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {

		if (modelValue == null) {
			return "";
		}

		return String.valueOf(((City) modelValue).getId());
	}

}
