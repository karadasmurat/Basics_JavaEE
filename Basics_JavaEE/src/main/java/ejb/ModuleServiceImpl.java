package ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Module;

@Stateless
public class ModuleServiceImpl implements ModuleService {

	private static final Logger LOGGER = Logger.getLogger(ModuleServiceImpl.class.getName());

	@PersistenceContext(unitName = "Basics_JavaEE_PU")
	EntityManager em;

	public ModuleServiceImpl() {
		LOGGER.log(Level.FINE, "MK: Constructing ModuleServiceImpl ");
	}

	@Override
	public Module findModuleById(long arg) {

		return em.find(Module.class, arg);

	}
	
	@Override
	public List<Module> findModules() {

		Query query = em.createNamedQuery("Module.findAll");

		return query.getResultList();
	
	}

}
