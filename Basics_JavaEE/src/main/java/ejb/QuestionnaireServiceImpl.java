package ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.questionnaire.Answer;
import entity.questionnaire.Choice;
import entity.questionnaire.Questionnaire;

@Stateless
public class QuestionnaireServiceImpl implements QuestionnaireService {
	
	private static final Logger LOGGER = Logger.getLogger(QuestionnaireServiceImpl.class.getName());

	@PersistenceContext(unitName = "Basics_JavaEE_PU")
	EntityManager em;
	
	
	@Override
	public void persistQuestionnaire(Questionnaire questionnaire) {		
		em.persist(questionnaire);
	}
	
	@Override
	public Questionnaire find(long qid) {
		return em.find(Questionnaire.class, qid);
	}
	
	@Override
	public Choice findChoice(long cid) {
		return em.find(Choice.class, cid);
	}
	
	@Override
	public void persistAnswer(Answer answer) {		
		em.persist(answer);
	}


}
