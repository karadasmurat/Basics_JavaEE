package entity.questionnaire;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Answer {
	
	@Id
	@GeneratedValue(generator = "AnswerIncGenerator")
	@GenericGenerator(name = "AnswerIncGenerator", strategy = "increment")
	@Column(name = "ID")
	private long id;
	
	//private User user
	
	//unidirectional
	@ManyToOne
	@JoinColumn(name="CHOICE_ID")
	private Choice response;
	
	public Answer() {
		super();
	}
	
	public Answer(Choice c) {
		this.response = c;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Choice getResponse() {
		return response;
	}
	public void setResponse(Choice response) {
		this.response = response;
	}
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Answer obj2 = (Answer) obj;

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getResponse().equals(obj2.getResponse()) ;
	}


	public int hashCode() {

		int result = 0;
		result = getResponse().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
	

}
