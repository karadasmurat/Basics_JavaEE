package entity.contactinfo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
})
public class City {
	
	@Id //no generator
	private long id;
	private String name;
	
	public City() {
		super();
	}
	
	public City(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		
		if (getName() == null || obj2.getName() == null) {
			return false;
		}

		return getName().equals(obj2.getName());
	}


	public int hashCode() {

		int result = 0;
		result = getName().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
	

}
