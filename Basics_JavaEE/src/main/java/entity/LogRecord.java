package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class LogRecord {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "ID")
	private long id;
	
	//unidirectional ManyToOne
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;
	
	@Column(name = "LOGTEXT")
	private String logText;
	
	public LogRecord(){
		
	}
	
	public LogRecord(User user, String log){
		this.user = user;
		this.logText = log;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLogText() {
		return logText;
	}

	public void setLogText(String logText) {
		this.logText = logText;
	}
	
	
	
	

}
