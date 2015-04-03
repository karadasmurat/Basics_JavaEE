package entity.questionnaire;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


public class QuestionGroup {
	
	private long id;
	
	private String text;
	List<Question> questions;
	private Questionnaire questionnaire;
	
	public QuestionGroup() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		QuestionGroup obj2 = (QuestionGroup) obj;

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getText().equals(obj2.getText());
	}


	public int hashCode() {

		int result = 0;
		result = getText().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}

}
