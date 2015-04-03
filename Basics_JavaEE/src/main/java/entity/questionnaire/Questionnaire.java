package entity.questionnaire;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Questionnaire {
	
	@Id
	@GeneratedValue(generator = "QuestionnaireIncGenerator")
	@GenericGenerator(name = "QuestionnaireIncGenerator", strategy = "increment")
	@Column(name = "ID")
	private long id;
	
	private String title;
	private String description;	
	//List<QuestionGroup> questionGroups;
	
	//bidirectional non-owning
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Question.class, mappedBy = "questionnaire", cascade = CascadeType.PERSIST)
	//List<Question> questions;
	Set<Question> questions;
	
	public Questionnaire() {
		super();
		initialize();
	}
	
	private void initialize(){

		questions = new HashSet<Question>(0);

	}
	
	//bidirectional add
	public void addQuestion(Question q){
		q.setQuestionnaire(this);
		this.questions.add(q);
	}
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Questionnaire obj2 = (Questionnaire) obj;

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getTitle().equals(obj2.getTitle());
	}


	public int hashCode() {

		int result = 0;
		result = getTitle().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
	public String toString(){
		
		String info = "Questionnaire: "+this.title;
		for(Question q: this.questions){
			info += " Question: "+q.getText();
		}
		
		return info;
		
	}

}
