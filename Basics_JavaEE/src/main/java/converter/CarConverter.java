package converter;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import entity.Car;

@Named
@SessionScoped
public class CarConverter implements Converter, Serializable {
	
	private static final Logger LOGGER = Logger.getLogger(CarConverter.class.getName());

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		return new Car(submittedValue);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		
		if (modelValue == null) {
			return "";
		}

		return ((Car)modelValue).getBrand();
		
	}

}
