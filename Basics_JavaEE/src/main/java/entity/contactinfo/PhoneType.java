package entity.contactinfo;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

@Entity
@NamedQueries({ 
	@NamedQuery(name="PhoneType.findAll", query="SELECT pt FROM PhoneType pt"),
	@NamedQuery(name="PhoneType.findByTitle", query="SELECT pt FROM PhoneType pt where pt.title = :title") 
})
public class PhoneType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "PhoneTypeIncGenerator")
	@GenericGenerator(name = "PhoneTypeIncGenerator", strategy = "increment")
	private long id;

	private String title;
	private String description;
	
	//all possible subtypes
	//The owning side
	@ManyToMany(fetch=FetchType.EAGER, targetEntity = PhoneSubType.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "PHONETYPE_SUBTYPE", joinColumns = { @JoinColumn(name = "PHONETYPE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PHONESUBTYPE_ID", referencedColumnName = "ID") })
	private Set<PhoneSubType> phoneSubTypes;

	
	public PhoneType() {
		title = "";
		description = "";
		phoneSubTypes = new HashSet<PhoneSubType>(0);
	}

	@PostUpdate
	public void postUpdate() {
		System.out.println("@PostUpdate");

	}

	@PostPersist
	public void postPersist() {
		System.out.println("@PostPersist");

	}

	@PostRemove
	public void postRemove() {
		System.out.println("@PostRemove");

	}
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		PhoneType obj2 = (PhoneType) obj;

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getTitle().equals(obj2.getTitle());
	}


	public int hashCode() {

		int result = 0;
		result = getTitle().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<PhoneSubType> getPhoneSubTypes() {
		return phoneSubTypes;
	}

	public void setPhoneSubTypes(Set<PhoneSubType> phoneSubTypes) {
		this.phoneSubTypes = phoneSubTypes;
	}	

}
