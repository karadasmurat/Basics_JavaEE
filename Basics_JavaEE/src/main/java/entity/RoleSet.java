package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ROLESET")
@NamedQueries({ 
	@NamedQuery(name = "RoleSet.findAll", query = "SELECT r FROM RoleSet r ORDER BY r.role"), 
	@NamedQuery(name = "RoleSet.findByRolename", query="SELECT r FROM RoleSet r where r.role = :rname")
})
public class RoleSet {

	@Id
	@GeneratedValue(generator = "RoleSetIncrement")
	@GenericGenerator(name = "RoleSetIncrement", strategy = "increment")
	@Column(name = "ID")
	private long id;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "ROLEGROUP")
	private String roleGroup;
	
	@Transient
	private Role roleWrapper;
	

	public RoleSet() {

	}
	
	public Role getAsRole(){
		
		roleWrapper = new Role();
		//roleWrapper.setId(this.id);
		roleWrapper.setRole(this.role);
		roleWrapper.setRoleGroup(this.roleGroup);
		
		return roleWrapper;
		
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

	public String getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}

	public String toString() {
		return role;
	}
	
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}

		RoleSet obj2 = (RoleSet) obj;

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
