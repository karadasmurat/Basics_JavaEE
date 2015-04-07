package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import entity.contactinfo.ContactInformation;

@Entity
@NamedQueries({ 
	@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
})
public class Company {
	
	@Id
	@GeneratedValue(generator = "CompanyIncGenerator")
	@GenericGenerator(name = "CompanyIncGenerator", strategy = "increment")
	private long id;
	
	private String name;
	private String description;
	
	//unidirectional
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="CONTACTINFO_ID")
	private	ContactInformation contactInformation;	
	
	//bidirectional
	@OneToMany( targetEntity = HumanResource.class, mappedBy = "company" )
	private List<HumanResource> humanResources;
	
	public Company() {
		super();
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

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	public void addHumanResource(HumanResource hr){
		hr.setCompany(this);
		this.humanResources.add(hr);
	}

	public List<HumanResource> getHumanResources() {
		return humanResources;
	}

	public void setHumanResources(List<HumanResource> humanResources) {
		this.humanResources = humanResources;
	}
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Company obj2 = (Company) obj;
		
		if (getName() == null || obj2.getName() == null) {
			return false;
		}

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getName().equals(obj2.getName()) ;
	}


	public int hashCode() {

		int result = 0;
		result = getName().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
}
