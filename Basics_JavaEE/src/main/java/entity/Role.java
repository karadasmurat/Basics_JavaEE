package entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ROLES")
@NamedQueries({ 
	@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r ORDER BY r.role"),
	@NamedQuery(name="Role.findByRolename", query="SELECT r FROM Role r where r.role = :rname")
})
public class Role {
	
	@Id
	@GeneratedValue(generator="RoleIncrement")
	@GenericGenerator(name="RoleIncrement", strategy = "increment")
    @Column(name="ID")
    private long id;
	
	@Column(name="ROLE")
    private String role;
	
	@Column(name="USERNAME")
    private String username;

	@Column(name="ROLEGROUP")
    private String roleGroup;
	
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;
	
	public Role(){
		
		this.roleGroup = "Roles";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.username = user.getUsername();
	}
/*	
	public String toString() {
		return role;
	}
	*/
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		Role obj2 = (Role) obj;

		// return getId() == obj2.getId() && getName().equals(obj2.getName());
		return getRole().equals(obj2.getRole());
	}


	public int hashCode() {

		int result = 0;
		result = getRole().hashCode();		
        result = 29 * result; // +getAnotherField();
        return result;

	}
}
