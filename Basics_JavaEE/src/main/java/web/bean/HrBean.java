package web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.ResourceService;
import ejb.TodoService;
import entity.Address;
import entity.Car;
import entity.HumanResource;

@Named
@SessionScoped
public class HrBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	ResourceService resourceService;
	
	List<Integer> intList;
	List<Car> cars;
	
	HumanResource humanResource;

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

	@PostConstruct
	public void initList() {
		
		humanResource = new HumanResource();
	}

	public String save() {
		// list now contains the values provided by the user.
		

		
		return "";

	}

	public String addAddress() {

		this.humanResource.getContactInfo().getAddresses().add(new Address("New Address"));
		
		return "";
	}
	
	public String createHR(){
		
		resourceService.persistHumanResource(this.humanResource);
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
	
	
	
}
