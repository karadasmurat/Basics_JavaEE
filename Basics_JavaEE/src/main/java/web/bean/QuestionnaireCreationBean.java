package web.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.QuestionnaireService;
import entity.questionnaire.ChoiceType;
import entity.questionnaire.Question;
import entity.questionnaire.Questionnaire;

@Named
@SessionScoped
public class QuestionnaireCreationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	QuestionnaireService questionnaireService;
	
	Questionnaire questionnaire;
	
	ChoiceType ct;
	
	@PostConstruct
	public void doInit() {
		
		questionnaire = new Questionnaire();
	}
	
	public String persistQuestionnaire(){
		
		questionnaireService.persistQuestionnaire(this.questionnaire);
		
		return "";
	}
	
	public String addQuestion(){
		
		this.questionnaire.addQuestion(new Question());
		
		return "";
	}


	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public ChoiceType getCt() {
		return ct;
	}
	
	public ChoiceType[] getChoiceTypes(){
		return this.ct.values();
	}

	public void setCt(ChoiceType ct) {
		this.ct = ct;
	}
	
	
	
	

}
