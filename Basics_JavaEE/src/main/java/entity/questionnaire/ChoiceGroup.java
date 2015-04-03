package entity.questionnaire;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

public class ChoiceGroup {
	

	private long id;
	
	private String text;
	private String description;
	
	private List<Choice> choices;
	
	//type as SELECTONE, SELECTMANY, INPUTTEXT
	private ChoiceType choiceType;
	
	public ChoiceGroup() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
		
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ChoiceType getChoiceType() {
		return choiceType;
	}

	public void setChoiceType(ChoiceType choiceType) {
		this.choiceType = choiceType;
	}

	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		ChoiceGroup obj2 = (ChoiceGroup) obj;

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
