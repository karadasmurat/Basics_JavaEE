package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Address implements Serializable {

	@Id
	@GeneratedValue(generator = "AddressIncGenerator")
	@GenericGenerator(name = "AddressIncGenerator", strategy = "increment")
	private long id;
	
	private String title;
	private String description;
	private String address;
	private String district;
	private Integer postCode;
	
	//unidirectional ManyToOne
	@ManyToOne
	@JoinColumn(name="CITYID")
	private City city; //TO ONE city (not a collection) (there is a column on the Many side TO ONE side)
	
	//bidirectional
	@ManyToMany( targetEntity = ContactInformation.class, mappedBy = "addresses" )
	private Set<ContactInformation> contactInformations;
	
	
	@CreationTimestamp
	@Column(name = "CREATION_DATE", updatable = false)
	private Calendar creationDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private Calendar updateDate;
	

	public Address() {
		super();
	}
	
	public Address(String title) {
		this.title= title;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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

	public Integer getPostCode() {
		return postCode;
	}

	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
	
	
	

}
