package entity;

public enum Status {

    SUBMITTED("submittedBundleKey"),
    REJECTED("rejectedBundleKey"),
    APPROVED("approvedBundleKey");

    private String desc;

    private Status(String arg) {
            this.desc = arg;
    }
}

