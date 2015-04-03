package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CERTIFICATE")
public class Certificate {

	@Id
	@GeneratedValue(generator = "CertificateIncrement")
	@GenericGenerator(name = "CertificateIncrement", strategy = "increment")
	@Column(name = "ID")
	private int id;

	@Column(name = "CERTIFICATE_NAME")
	private String name;

	@ManyToMany(mappedBy = "certificates", targetEntity = Employee.class)
	private Set employees;

	public Certificate() {
		employees = new HashSet(0);
	}

	public Certificate(String name) {
		employees = new HashSet(0);
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set getEmployees() {
		return employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	/*
	 * Generated identifiers: Hibernate will only assign identifier values to
	 * objects that are persistent; a newly created instance will not have any
	 * identifier value
	 * 
	 * It is recommended that you implement equals() and hashCode() using
	 * Business key equality. Business key equality means that the equals()
	 * method compares only the properties that form the business key.
	 */
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Certificate obj2 = (Certificate) obj;

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getName().equals(obj2.getName());
	}

	/*
	 * A good hash function tends to produce unequal hash codes for unequal
	 * objects.
	 */
	public int hashCode() {

		int result = 0;
		result = getName().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

		//tmp = (new StringBuilder(String.valueOf(id))).append(name).toString().hashCode();

	}

	public String toString() {
		return getId() + ":" + getName();
	}


	
	

}
