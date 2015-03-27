package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {
	
	@Id //no generator
	private String id;
	private String name;
	
	public City() {
		super();
	}
	
	public City(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setCode(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		City obj2 = (City) obj;

		return getName().equals(obj2.getName());
	}


	public int hashCode() {

		int result = 0;
		result = getName().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
	

}
