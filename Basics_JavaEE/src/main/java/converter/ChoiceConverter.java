package converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ejb.QuestionnaireService;
import entity.Role;
import entity.questionnaire.Choice;

@ManagedBean
@RequestScoped
public class ChoiceConverter implements Converter {

	private static final Logger LOGGER = Logger.getLogger(ChoiceConverter.class.getName());

	@EJB
	private QuestionnaireService questionnaireService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {

		// This method is called when HTTP request parameter is to be converted
		// to item value.

		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		Long cid = Long.valueOf(submittedValue);
		Choice choice = questionnaireService.findChoice(cid);
		return choice;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {

		if (modelValue == null) {
			return "";
		}

		return String.valueOf(((Choice) modelValue).getId());
	}

}
