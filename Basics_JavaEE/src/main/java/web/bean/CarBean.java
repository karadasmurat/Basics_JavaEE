package web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entity.Car;
import java.io.Serializable;

@Named
@SessionScoped
public class CarBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Car> allCars;
	private List<Car> selectedCars;
	
	public CarBean(){
		
		allCars = new ArrayList<Car>(0);
		selectedCars = new ArrayList<Car>(0);
		
		generateCars();
	}
	
	@PostConstruct
	public void initialize() {		
		
	}
	
	private void generateCars(){
		
		Car car1 = new Car("Ford");
		Car car2 = new Car("Opel");
		Car car3 = new Car("BMW");
		
		allCars.add(car1);
		allCars.add(car2);
		allCars.add(car3);		
		
	}

	public List<Car> getAllCars() {
		return allCars;
	}

	public List<Car> getSelectedCars() {
		return selectedCars;
	}

	public void setSelectedCars(List<Car> selectedCars) {
		this.selectedCars = selectedCars;
	}
	
	
	
	

}
