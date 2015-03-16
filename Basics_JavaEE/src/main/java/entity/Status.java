package entity;

public enum Status {

    SUBMITTED("submittedBundleKey"),
    REJECTED("rejectedBundleKey"),
    APPROVED("approvedBundleKey"),
    
	NOTSTARTED("Not started"),
	INPROGRESS("In progress"),
	COMPLETED("Completed"),
    
    LOCKED("Account Locked"),
    ACTIVE("Active");

    private String desc;

    private Status(String arg) {
            this.desc = arg;
    }
}

