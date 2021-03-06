package entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(generator = "UserIncrement")
	@GenericGenerator(name = "UserIncrement", strategy = "increment")
	@Column(name = "ID")
	private long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String hashedPassword;

	@Column(name = "RAWPASSWORD")
	private String rawPassword;

	@Column(name = "LOGINATTEMPTS")
	private String loginAttempts;

	private String firstName;
	private String lastName;
	private String email;

	@OneToMany(targetEntity = Role.class, mappedBy = "user", cascade = CascadeType.PERSIST)
	private Set<Role> roles;

	private String salt;

	@CreationTimestamp
	@Column(name = "CREATION_DATE", updatable = false)
	private Calendar creationDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private Calendar updateDate;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Version
	private long version;

	public User() {
		this.roles = new HashSet<Role>(0);
	}

	public void addRole(Role role) {
		role.setUser(this);
		this.roles.add(role);
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {

		for (Role r : roles) {
			r.setUser(this);
		}

		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(String loginAttempts) {
		this.loginAttempts = loginAttempts;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
