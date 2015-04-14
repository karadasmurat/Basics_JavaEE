package entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import entity.contactinfo.Address;

@Entity
@NamedQueries({ 
	@NamedQuery(name="Module.findAll", query="SELECT m FROM Module m"),
	@NamedQuery(name = "Module.findByName", query = "SELECT c FROM Module c WHERE c.name = :name"),
})
public class Module {
	
	@Id
	@GeneratedValue(generator = "ModuleIncGenerator")
	@GenericGenerator(name = "ModuleIncGenerator", strategy = "increment")
	private Long id;
	
	private String name;
	private String description;
	private String homePage;
	private String icon;
	
	//bidirectional
	@OneToMany(fetch=FetchType.EAGER, targetEntity = ModuleLink.class, mappedBy="module", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<ModuleLink> moduleLinks;
	
	public Module() {
		super();
	}
	
	public Module(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public void addNavigation(ModuleLink nav){
		//bidirectional addToList
		nav.setModule(this);
		moduleLinks.add(nav);
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

	public void setCode(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}	

	public Set<ModuleLink> getModuleLinks() {
		return moduleLinks;
	}

	public void setModuleLinks(Set<ModuleLink> moduleLinks) {
		this.moduleLinks = moduleLinks;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public boolean equals(Object arg) {
		
		/*
		* THIS	OTHER(not null)
		* ----	-----
		* null	null	:fail
		* null	value	:fail
		* value	null	:fail
		* value	value	:compare
		*/
		
		if (this == arg) {
			return true;
		}

		if ((arg == null) || (arg.getClass() != this.getClass())) {
			return false;
		}

		Module other = (Module) arg;
		
		if (this.name == null || other.name == null ) {
			return false;
		}
        
		return this.name.equals(other.name);
	}

	@Override
	public int hashCode() {

		int result = 0;
		result += (this.name != null ? this.name.hashCode() : 0);		
        result = 29 * result; // +getAnotherField();
        return result;

	}
	
    @Override
    public String toString() {
        return "entity.Module[ name=" + name + " ]";
    }
	
	

}
