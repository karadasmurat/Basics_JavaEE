package web.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import ejb.ModuleService;
import entity.Car;
import entity.Company;
import entity.HumanResource;
import entity.Module;
import entity.contactinfo.Address;
import entity.contactinfo.City;
import entity.contactinfo.PhoneNumber;
import entity.contactinfo.PhoneSubType;
import entity.contactinfo.PhoneType;

@Named
@SessionScoped
public class ModuleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(ModuleBean.class.getName());

	@EJB
	ModuleService moduleService;

	Module currentModule;
	List<Module> allModules;


	public ModuleBean() {
		super();
		currentModule = new Module();
	}

	@PostConstruct
	public void initialize() {
		currentModule = moduleService.findModuleById(1L);
		allModules = moduleService.findModules();
	}
	
	

	public List<Module> getAllModules() {
		return allModules;
	}

	public void setAllModules(List<Module> allModules) {
		this.allModules = allModules;
	}
	
	public Module getCurrentModule() {
		return currentModule;
	}

	public void setCurrentModule(Module currentModule) {
		this.currentModule = currentModule;
	}

	public String prepareModuleDetails(Module module){
		
		this.currentModule = module;
		
		return currentModule.getHomePage();
	}
	
	
}