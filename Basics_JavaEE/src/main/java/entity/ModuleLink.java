package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

@Entity
@NamedQueries({
	@NamedQuery(name="ModuleLink.findAll", query="SELECT n FROM ModuleLink n"),
	@NamedQuery(name = "ModuleLink.findByName", query = "SELECT n FROM ModuleLink n WHERE n.name = :name"),
})
public class ModuleLink {
	
	@Id
	@GeneratedValue(generator = "ModuleLinkIncGenerator")
	@GenericGenerator(name = "ModuleLinkIncGenerator", strategy = "increment")
	private Long id;
	
	private String name;
	private String description;
	private String href;
	private String icon;
	
	//bidirectional owning
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="MODULE_ID")
	private Module module;
	
	public ModuleLink() {
		super();
	}
	
	public ModuleLink(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
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

		ModuleLink other = (ModuleLink) arg;
		
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
