package entity.contactinfo;

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

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "PhoneSubTypeIncGenerator")
	@GenericGenerator(name = "PhoneSubTypeIncGenerator", strategy = "increment")
	private Long id;

	private String title;
	private String description;	

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

		PhoneSubType other = (PhoneSubType) arg;
		
		if (this.title == null || other.title == null ) {
			return false;
		}
        
		return this.title.equals(other.title);
	}

	@Override
	public int hashCode() {

		int result = 0;	
		result += (this.title != null ? this.title.hashCode() : 0);
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
    @Override
    public String toString() {
        return "entity.contactinfo.PhoneSubType[ id=" + id + " ]";
    }
	
	

}
