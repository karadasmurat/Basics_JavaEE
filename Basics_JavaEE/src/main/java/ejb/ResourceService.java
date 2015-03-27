package ejb;

import javax.ejb.Local;

import entity.HumanResource;

@Local
public interface ResourceService {

	void persistHumanResource(HumanResource hres);
	

}
