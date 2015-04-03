package web.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.QuestionnaireService;
import entity.questionnaire.Answer;
import entity.questionnaire.Choice;
import entity.questionnaire.Question;
import entity.questionnaire.Questionnaire;

@Named
@SessionScoped
public class QuestionnaireBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(QuestionnaireBean.class.getName());

	@EJB
	QuestionnaireService questionnaireService;

	private Questionnaire questionnaire;
	private long queryID;

	// private List<Choice> selectedChoices;
	private Map<Long, Choice[]> selectionMap = new HashMap<Long, Choice[]>();

	public QuestionnaireBean() {
		// questionnaire = new Questionnaire();
	}

	public void initValues() {
		questionnaire = questionnaireService.find(queryID);
		LOGGER.log(Level.FINE, "MK: Questionnaire info " + questionnaire.toString());
	}

	public long getQueryID() {
		return queryID;
	}

	public void setQueryID(long queryID) {
		this.queryID = queryID;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Map<Long, Choice[]> getSelectionMap() {
		return selectionMap;
	}

	public void setSelectionMap(Map<Long, Choice[]> selectionMap) {
		this.selectionMap = selectionMap;
	}

	public String persistQuestionnaire() {

		return "";
	}

	public String saveResponse() {

		/*
		for ( Long key : selectionMap.keySet()) {
			
			Choice[] choices = (Choice[]) selectionMap.get(key);

			if (choices != null) {

				for (Choice c : choices) {

					if (c != null) {
							
						Answer ans = new Answer(c);
						questionnaireService.persistAnswer(ans);
					}
				}
			}
		}
		 */
		
		for( Question q : this.questionnaire.getQuestions()){
			
			for(Choice c : q.getSelectedChoices()){
				
				Answer ans = new Answer(c);
				questionnaireService.persistAnswer(ans);
				
			}
			
		}
		return "";
	}

}
