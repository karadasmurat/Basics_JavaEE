package entity;

public class Car {
	
	private String Brand;
	private String Model;
	private Number Year;
	
	public Car(){
		super();
	}
		
	public Car(String brand) {
		super();
		Brand = brand;
	}



	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public Number getYear() {
		return Year;
	}
	public void setYear(Number year) {
		Year = year;
	}
	
	public String getLabel(){
		return Model+"-"+Brand;
	}
	
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Car obj2 = (Car) obj;

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getBrand().equals(obj2.getBrand());
	}


	public int hashCode() {

		int result = 0;
		result = getBrand().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
	

}
