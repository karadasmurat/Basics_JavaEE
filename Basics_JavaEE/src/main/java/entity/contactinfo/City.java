package entity.contactinfo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name="City.findAll", query="SELECT c FROM City c"),
	@NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name = :name"),
})
public class City {
	
	@Id //no generator
	private Long id;
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
	
	@Override
	public boolean equals(Object arg) {
		
		/*
		* THIS	OTHER(not null)
		* ----	-----
		* null	null	:fail
		* null	value	:fail
		* value	null	:fail
		* value	value	:compare
		*/
		
		if (this == arg) {
			return true;
		}

		if ((arg == null) || (arg.getClass() != this.getClass())) {
			return false;
		}

		City other = (City) arg;
		
		//id is not DB generated, it is dim fix.
		if (this.id == null || other.id == null ) {
			return false;
		}
        
		return this.id.equals(other.id);
	}

	@Override
	public int hashCode() {

		int result = 0;
		result += (this.id != null ? this.id.hashCode() : 0);		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
    @Override
    public String toString() {
        return "entity.contactinfo.City[ id=" + id + " ]";
    }
	
	

}
