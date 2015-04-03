package web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import entity.Car;

@Named
@SessionScoped
public class RepeatBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<Integer> intList;
	List<Car> cars;

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

	@PostConstruct
	public void initList() {
		intList = new ArrayList<Integer>();
		cars = new ArrayList<Car>(1);
		
		intList.add(10);
		intList.add(20);
		intList.add(30);
		
		cars.add(new Car("Initial"));
	}

	public String save() {
		// list now contains the values provided by the user.
		return "";

	}

	public String add() {

		//intList.add(0);
		Car newCar = new Car("Brand");
		cars.add(newCar);
		
		return "";

	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	

}
