package ejb;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Company;
import entity.HumanResource;
import entity.contactinfo.City;
import entity.contactinfo.PhoneSubType;
import entity.contactinfo.PhoneType;

@Stateless
public class ResourceServiceImpl implements ResourceService {

	private static final Logger LOGGER = Logger.getLogger(ResourceServiceImpl.class.getName());

	@PersistenceContext(unitName = "Basics_JavaEE_PU")
	EntityManager em;

	@Override
	public void persistHumanResource(HumanResource hres) {
		em.persist(hres);
	}
	
	@Override
	public void mergeHumanResource(HumanResource hres) {
		em.merge(hres);
	}

	@Override
	public Company findCompany(Long cid) {
		// TODO Auto-generated method stub
		return em.find(Company.class, cid);
	}
	
	@Override
	public List<Company> findCompanies() {

		Query query = em.createNamedQuery("Company.findAll");

		return query.getResultList();
	
	}
	
	@Override
	public City findCity(Long cid) {
		// TODO Auto-generated method stub
		return em.find(City.class, cid);
	}
	
	@Override
	public List<City> findCities() {

		Query query = em.createNamedQuery("City.findAll");

		return query.getResultList();

	}

	@Override
	public PhoneType findPhoneType(Long pid) {

		return em.find(PhoneType.class, pid);
	}
	
	@Override
	public List<PhoneType> findPhoneTypes() {

		Query query = em.createNamedQuery("PhoneType.findAll");

		return query.getResultList();

	}

	@Override
	public PhoneSubType findPhoneSubType(Long pid) {

		return em.find(PhoneSubType.class, pid);
	}
	
	@Override
	public List<PhoneSubType> findPhoneSubTypes() {

		Query query = em.createNamedQuery("PhoneSubType.findAll");

		return query.getResultList();

	}

	@Override
	public List<HumanResource> findHumanResources() {
		
		Query query = em.createNamedQuery("HumanResource.findAll");

		return query.getResultList();
	}

}
