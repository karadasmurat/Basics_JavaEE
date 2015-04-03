package entity.questionnaire;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Choice {
	
	@Id
	@GeneratedValue(generator = "ChoiceIncGenerator")
	@GenericGenerator(name = "ChoiceIncGenerator", strategy = "increment")
	@Column(name = "ID")
	private long id;
	
	private String text;
	private boolean isSelected;
	
	//bidirectional owning
	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	private Question question;
		
	public Choice() {
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
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	} 

	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Choice obj2 = (Choice) obj;

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
