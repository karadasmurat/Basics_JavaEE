package ejb;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import entity.Module;
import entity.Role;
import entity.RoleSet;
import entity.User;
import entity.contactinfo.PhoneSubType;
import entity.contactinfo.PhoneType;

@Local
public interface ModuleService {

	Module findModuleById(long arg);

	List<Module> findModules();


}
