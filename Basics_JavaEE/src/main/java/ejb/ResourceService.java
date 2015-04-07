package ejb;

import java.util.List;

import javax.ejb.Local;

import entity.Company;
import entity.HumanResource;
import entity.contactinfo.City;
import entity.contactinfo.PhoneSubType;
import entity.contactinfo.PhoneType;

@Local
public interface ResourceService {

	void persistHumanResource(HumanResource hres);
	void mergeHumanResource(HumanResource hres);

	Company findCompany(Long cid);
	List<Company> findCompanies();
	
	City findCity(Long cid);
	List<City> findCities();
	
	PhoneType findPhoneType(Long pid);
	List<PhoneType> findPhoneTypes();
	
	PhoneSubType findPhoneSubType(Long pid);
	List<PhoneSubType> findPhoneSubTypes();
	

	
	

}
