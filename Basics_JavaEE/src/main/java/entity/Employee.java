package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(generator = "EmployeeIncrement")
	@GenericGenerator(name = "EmployeeIncrement", strategy = "increment")
	@Column(name = "ID")
	private int id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "SALARY")
	private int salary;

	@ManyToMany(targetEntity = Certificate.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "EMP_CERT", joinColumns = { @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "CERTIFICATE_ID", referencedColumnName = "ID") })
	private Set certificates;

	@Version
	private long version;

	public Employee() {
		certificates = new HashSet(0);
	}

	public Employee(String fname, String lname, int salary) {
		certificates = new HashSet(0);
		firstName = fname;
		lastName = lname;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		lastName = last_name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Set getCertificates() {
		return certificates;
	}

	public void setCertificates(Set certificates) {
		this.certificates = certificates;
	}

	public void addCertificate(Certificate cert) {
		getCertificates().add(cert);
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
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

		Employee temp = (Employee) obj;

		return getLastName().equals(temp.getLastName());
	}

	/*
	 * A good hash function tends to produce unequal hash codes for unequal
	 * objects.
	 */
	public int hashCode() {

		int result = 0;
		result = getLastName().hashCode();
		result = 29 * result + getFirstName().hashCode();
		return result;

	}

	public String toString() {

		return getLastName() + ", " + getFirstName();
	}

}
