package entity.contactinfo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

@Entity
public class PhoneNumber implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "PhoneNumberIncGenerator")
	@GenericGenerator(name = "PhoneNumberIncGenerator", strategy = "increment")
	private long id;
	
	private String title;
	private String code;
	private String phoneNumber;
	private String ext;
	
	//unidirectional ManyToOne
	@ManyToOne
	@JoinColumn(name="PHONETYPE_ID")
	private PhoneType phoneType;
	
	//unidirectional ManyToOne
	@ManyToOne
	@JoinColumn(name="PHONESUBTYPE_ID")
	private PhoneSubType phoneSubType;
	
	
	//@ManyToMany( targetEntity = ContactInformation.class, mappedBy = "phoneNumbers" )
	//private Set<ContactInformation> contactInformations;
	
	//bidirectional owning
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="CONTACTINFO_ID")
	private ContactInformation contactInformation;
	
	
	@CreationTimestamp
	@Column(name = "CREATION_DATE", updatable = false)
	private Calendar creationDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private Calendar updateDate;

	public PhoneNumber() {
		super();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
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

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public PhoneSubType getPhoneSubType() {
		return phoneSubType;
	}

	public void setPhoneSubType(PhoneSubType phoneSubType) {
		this.phoneSubType = phoneSubType;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	

}
