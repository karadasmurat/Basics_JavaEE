package entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "TBL_TODO")
@NamedQueries({ 
	@NamedQuery(name="Todo.findAll", query="SELECT t FROM Todo t"),
	@NamedQuery(name="Todo.findByTitle", query="SELECT t FROM Todo t where t.title = :title") 
})
public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IncGenerator")
	@GenericGenerator(name = "IncGenerator", strategy = "increment")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceIdGenerator")
	//@SequenceGenerator(name = "SequenceIdGenerator", sequenceName = "HIBERNATE_SEQUENCE", allocationSize=1)
	@Column(name = "ID")
	// select max(ID) from TBL_TODO + 1
	private long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@CreationTimestamp
	@Column(name = "CREATION_DATE", updatable = false)
	private Calendar creationDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private Calendar updateDate;
	
	@Enumerated(EnumType.STRING)
	private TodoStatus status;
	
	@Version
	private long version;

	public Todo() {
		title = "";
		description = "";
		status = TodoStatus.NOTSTARTED;
	}

	@PostUpdate
	public void postUpdate() {
		System.out.println("@PostUpdate");

	}

	@PostPersist
	public void postPersist() {
		System.out.println("@PostPersist");

	}

	@PostRemove
	public void postRemove() {
		System.out.println("@PostRemove");

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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	

}
