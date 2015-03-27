package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity 
public class ContactInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "ContactIncGenerator")
	@GenericGenerator(name = "ContactIncGenerator", strategy = "increment")
	private long id;

	private String title;
	private String description;
	
	//The owning side
	@ManyToMany(fetch=FetchType.EAGER, targetEntity = Address.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CONTACTINFO_ADDRESS", joinColumns = { @JoinColumn(name = "CONTACTINFO_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID") })
	private List<Address> addresses;
	
	//The owning side
	@ManyToMany(fetch=FetchType.EAGER, targetEntity = PhoneNumber.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CONTACTINFO_PHONENUMBER", joinColumns = { @JoinColumn(name = "CONTACTINFO_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PHONENUMBER_ID", referencedColumnName = "ID") })
	private List<PhoneNumber> phoneNumbers;
	
	@CreationTimestamp
	@Column(name = "CREATION_DATE", updatable = false)
	private Calendar creationDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private Calendar updateDate;

	public ContactInformation() {
		super();
		initialize();
	}
	
	private void initialize(){

		phoneNumbers = new ArrayList<PhoneNumber>(1);
		addresses = new ArrayList<Address>(1);
		
		this.addresses.add(new Address("CI Constructor"));
		this.phoneNumbers.add(new PhoneNumber());
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
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
