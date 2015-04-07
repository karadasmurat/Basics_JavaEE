package entity.contactinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Address.class, mappedBy="contactInformation", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Address> addresses;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity = PhoneNumber.class, mappedBy="contactInformation", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
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
		
		//bidirectional addToList
		this.addAddress(new Address());
		this.addPhoneNumber(new PhoneNumber());
	}
	
	public void addAddress(Address addr){
		//bidirectional addToList
		addr.setContactInformation(this);
		addresses.add(addr);
	}
	
	public void addPhoneNumber(PhoneNumber p){
		//bidirectional addToList
		p.setContactInformation(this);
		this.phoneNumbers.add(p);
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
