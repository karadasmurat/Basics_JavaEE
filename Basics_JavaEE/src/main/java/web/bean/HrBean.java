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

import ejb.ResourceService;
import ejb.ResourceServiceImpl;
import entity.Car;
import entity.Company;
import entity.HumanResource;
import entity.contactinfo.Address;
import entity.contactinfo.City;
import entity.contactinfo.PhoneNumber;
import entity.contactinfo.PhoneSubType;
import entity.contactinfo.PhoneType;

@Named
@SessionScoped
public class HrBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(HrBean.class.getName());

	@EJB
	ResourceService resourceService;

	List<Integer> intList;
	List<Car> cars;

	List<Company> allCompanies;
	List<City> allCities;

	private List<PhoneType> allPhoneTypes;
	private Set<PhoneSubType> allPhoneSubTypes;

	HumanResource humanResource;

	public HrBean() {
		super();
		humanResource = new HumanResource();
	}

	@PostConstruct
	public void initialize() {
		allCompanies = resourceService.findCompanies();
		allCities = resourceService.findCities();
		allPhoneTypes = resourceService.findPhoneTypes();

		// will be triggered by phoneType change event.
		// allPhoneSubTypes = resourceService.findPhoneSubTypes();
	}

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

	public String save() {
		// list now contains the values provided by the user.

		return "";

	}

	public String addAddress() {

		this.humanResource.getContactInfo().addAddress(new Address("Address Information"));

		return "";
	}

	public String addPhone() {

		this.humanResource.getContactInfo().addPhoneNumber(new PhoneNumber());

		return "";
	}

	public String createHR() {

		// resourceService.persistHumanResource(this.humanResource);

		// merge - company gives detached exception //converter.
		resourceService.mergeHumanResource(this.humanResource);

		return "";
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public HumanResource getHumanResource() {
		return humanResource;
	}

	public void setHumanResource(HumanResource humanResource) {
		this.humanResource = humanResource;
	}

	public List<Company> getAllCompanies() {
		return allCompanies;
	}

	public void setAllCompanies(List<Company> allCompanies) {
		this.allCompanies = allCompanies;
	}

	public List<City> getAllCities() {
		return allCities;
	}

	public void setAllCities(List<City> allCities) {
		this.allCities = allCities;
	}

	public List<PhoneType> getAllPhoneTypes() {
		return allPhoneTypes;
	}

	public void setAllPhoneTypes(List<PhoneType> allPhoneTypes) {
		this.allPhoneTypes = allPhoneTypes;
	}

	public Set<PhoneSubType> getAllPhoneSubTypes() {
		return allPhoneSubTypes;
	}

	public void setAllPhoneSubTypes(Set<PhoneSubType> allPhoneSubTypes) {
		this.allPhoneSubTypes = allPhoneSubTypes;
	}

	public void phoneTypeChanged(ValueChangeEvent event) {

		PhoneType pt = (PhoneType) event.getNewValue();

		allPhoneSubTypes = pt.getPhoneSubTypes();

	}

	public void processValueChange(AjaxBehaviorEvent event) {
		
		PhoneType pt = (PhoneType) ((UIOutput)event.getSource()).getValue();
		
		LOGGER.log(Level.FINE, "MK AjaxBehaviorEvent title: "+ pt.getTitle() ) ;
		LOGGER.log(Level.FINE, "MK AjaxBehaviorEvent subType count: "+ pt.getPhoneSubTypes().size() ) ;
		
		allPhoneSubTypes = pt.getPhoneSubTypes(); 
		
		LOGGER.log(Level.FINE, "allPhoneSubTypes count: "+ allPhoneSubTypes.size() ) ;
	}

}
