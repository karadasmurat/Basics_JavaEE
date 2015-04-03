package entity.questionnaire;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;



@Entity
public class Question {
	
	@Id
	@GeneratedValue(generator = "QuestionIncGenerator")
	@GenericGenerator(name = "QuestionIncGenerator", strategy = "increment")
	@Column(name = "ID")
	private long id;
	
	private String text;

	//private QuestionGroup questionGroup;
	
	//bidirectional owning
	@ManyToOne
	@JoinColumn(name="QUESTIONNAIRE_ID")
	private Questionnaire questionnaire;
	
	//private ChoiceGroup choiceGroup;
	
	//bidirectional non-owning     
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Choice.class, mappedBy = "question", cascade = CascadeType.PERSIST)
	private List<Choice> choices;
	
	@Transient
	private List<Choice> selectedChoices;
	
	@Enumerated(EnumType.STRING)
	private ChoiceType choiceType;
	
	public Question() {
		super();
		initialize();
	}
	
	private void initialize(){

		this.text="";
		choices = new ArrayList<Choice>(1);
		
		Choice c = new Choice();
		c.setQuestion(this);
		choices.add(c);

	}
	
	//bidirectional add
	public void addChoice(Choice c){
		c.setQuestion(this);
		this.choices.add(c);		
	}
	
	//bidirectional add
	public void addDummyChoice(){
		Choice c = new Choice();
		c.setQuestion(this);
		this.choices.add(c);		
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
	
	
	
	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public ChoiceType getChoiceType() {
		return choiceType;
	}

	public void setChoiceType(ChoiceType choiceType) {
		this.choiceType = choiceType;
	}

	public List<Choice> getSelectedChoices() {
		return selectedChoices;
	}

	public void setSelectedChoices(List<Choice> selectedChoices) {
		this.selectedChoices = selectedChoices;
	}

	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Question obj2 = (Question) obj;

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
