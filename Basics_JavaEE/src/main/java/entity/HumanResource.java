package entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import entity.contactinfo.ContactInformation;

/**
 * Entity implementation class for Entity: HumanResource
 *
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name="HumanResource.findAll", query="SELECT h FROM HumanResource h"),
	@NamedQuery(name = "HumanResource.findByLastName", query = "SELECT h FROM HumanResource h WHERE h.lastName = :lastName")
})
public class HumanResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "HRIncGenerator")
	@GenericGenerator(name = "HRIncGenerator", strategy = "increment")
	private long id;
	
	private String employeeId;
	private String firstName;
	private String lastName;
	private String primaryEmail;
	private String secondaryEmail;
	private String title;
	
	//bidirectional owning
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="COMPANY_ID")
	private Company company;
	
	private String groupName;
	
	//unidirectional ManyToOne
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="CONTACTINFO_ID")
	private ContactInformation contactInfo;
	
	@CreationTimestamp
	@Column(name = "CREATION_DATE", updatable = false)
	private Calendar creationDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private Calendar updateDate;

	public HumanResource() {
		super();
		contactInfo = new ContactInformation();
	}   	

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		//bidirectional
		this.company = company;
		//company.getHumanResources().add(this);
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getSecondaryEmail() {
		return secondaryEmail;
	}
	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}
	public ContactInformation getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInformation contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Calendar getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Calendar updateDate) {
		this.updateDate = updateDate;
	}
	
	
   
}
