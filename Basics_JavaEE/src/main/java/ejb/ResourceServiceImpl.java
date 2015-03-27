package ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.HumanResource;

@Stateless
public class ResourceServiceImpl implements ResourceService {
	
	private static final Logger LOGGER = Logger.getLogger(ResourceServiceImpl.class.getName());

	@PersistenceContext(unitName = "Basics_JavaEE_PU")
	EntityManager em;
	
	
	@Override
	public void persistHumanResource(HumanResource hres) {		
		em.persist(hres);
	}


}
