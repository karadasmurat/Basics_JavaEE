package web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import ejb.UserService;
import entity.contactinfo.PhoneSubType;
import entity.contactinfo.PhoneType;

@Named
@SessionScoped
public class SelectOneBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userService;

	private static final Logger LOGGER = Logger.getLogger(SelectOneBean.class.getName());

	private List<PhoneType> allPhoneTypes;
	private PhoneType selectedPhoneType;
	
	private Set<PhoneSubType> allPhoneSubTypes;
	private PhoneSubType selectedPhoneSubType;

	public SelectOneBean() {
		
		allPhoneTypes = new ArrayList<PhoneType>(0);	
		
	}

	@PostConstruct
	public void initialize() {

		initTypes();
	}

	private void initTypes() {		

		/*
		 * allTypes.add("Mobile"); allTypes.add("Fix");
		 * allTypes.add("Satellite");
		 */
		
		allPhoneTypes = userService.findPhoneTypes();
		
	}

	public void typeChanged(ValueChangeEvent event) {
		
		LOGGER.log(Level.FINE, "MK: Value changed: " + event.getNewValue().toString());

		PhoneType pt = (PhoneType) event.getNewValue();
		
		setAllPhoneSubTypes( pt.getPhoneSubTypes() );
		
		//generateLevel2List(event.getNewValue());
	}
	
	public void generateLevel2List() {

		if(selectedPhoneType != null){
			LOGGER.log(Level.FINE, "MK: Generating Level 2 List for: " + selectedPhoneType.getTitle());
			//LOGGER.log(Level.FINE, "MK: PhoneSubTypes: " + selectedPhoneType.getPhoneSubTypes().size());
		}else{
			LOGGER.log(Level.FINE, "MK: selectedPhoneType is null");
		}
		
		//setAllPhoneSubTypes(selectedPhoneType.getPhoneSubTypes());	
		
	}
	
	private void initSubTypes() {		

		/*
		 * allTypes.add("Mobile"); allTypes.add("Fix");
		 * allTypes.add("Satellite");
		 */
		
		allPhoneTypes = userService.findPhoneTypes();
		
	}


	
	public void subTypeChanged() {

		LOGGER.log(Level.FINE, "subTypeChanged");
		
	}

	public List<PhoneType> getAllPhoneTypes() {
		return allPhoneTypes;
	}

	public void setAllPhoneTypes(List<PhoneType> allPhoneTypes) {
		this.allPhoneTypes = allPhoneTypes;
	}

	public PhoneType getSelectedPhoneType() {
		return selectedPhoneType;
	}

	public void setSelectedPhoneType(PhoneType selectedPhoneType) {
		this.selectedPhoneType = selectedPhoneType;
	}

	public Set<PhoneSubType> getAllPhoneSubTypes() {
		return allPhoneSubTypes;
	}

	public void setAllPhoneSubTypes(Set<PhoneSubType> allPhoneSubTypes) {
		this.allPhoneSubTypes = allPhoneSubTypes;
	}

	public PhoneSubType getSelectedPhoneSubType() {
		return selectedPhoneSubType;
	}

	public void setSelectedPhoneSubType(PhoneSubType selectedPhoneSubType) {
		this.selectedPhoneSubType = selectedPhoneSubType;
	}
	
	

}
