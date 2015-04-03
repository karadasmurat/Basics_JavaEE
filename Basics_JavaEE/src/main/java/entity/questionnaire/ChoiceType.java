package entity.questionnaire;

public enum ChoiceType {

	SELECTONE("SELECTONE"), 
	SELECTMANY("SELECTMANY"), 
	INPUTTEXT("INPUTTEXT");

	private String label;

	private ChoiceType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
