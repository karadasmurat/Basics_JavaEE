package ejb;

import javax.ejb.Local;

import entity.questionnaire.Answer;
import entity.questionnaire.Choice;
import entity.questionnaire.Questionnaire;

@Local
public interface QuestionnaireService {

	void persistQuestionnaire(Questionnaire questionnaire);

	Questionnaire find(long qid);

	void persistAnswer(Answer answer);

	Choice findChoice(long cid);

}
