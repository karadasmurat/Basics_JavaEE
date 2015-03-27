package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

@Entity
@NamedQueries({
		@NamedQuery(name = "PhoneSubType.findAll", query = "SELECT pst FROM PhoneSubType pst"),
		@NamedQuery(name = "PhoneSubType.findByTitle", query = "SELECT pst FROM PhoneSubType pst where pst.title = :title") })
public class PhoneSubType implements Serializable {

	@Id
	@GeneratedValue(generator = "PhoneSubTypeIncGenerator")
	@GenericGenerator(name = "PhoneSubTypeIncGenerator", strategy = "increment")
	private Long id;

	private String title;
	private String description;
	private static final long serialVersionUID = 1L;

	@ManyToMany( targetEntity = PhoneType.class, mappedBy = "phoneSubTypes" )
	private Set<PhoneType> phoneTypes;


	public PhoneSubType() {
		super(); 
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<PhoneType> getPhoneTypes() {
		return phoneTypes;
	}

	public void setPhoneTypes(Set<PhoneType> phoneTypes) {
		this.phoneTypes = phoneTypes;
	}
	
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		PhoneSubType obj2 = (PhoneSubType) obj;

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getTitle().equals(obj2.getTitle());
	}


	public int hashCode() {

		int result = 0;
		result = getTitle().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
	

}
