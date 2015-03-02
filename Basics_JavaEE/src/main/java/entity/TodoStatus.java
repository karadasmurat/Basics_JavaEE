package entity;

public enum TodoStatus {
	
	NOTSTARTED("Not started"),
	INPROGRESS("In progress"),
	COMPLETED("Completed");
	
	private String desc;
	
	private TodoStatus(String arg){
		this.desc = arg;
	}
	

}
