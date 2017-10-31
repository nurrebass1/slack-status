package slack.constants;


public enum Status {
	AWAY("away"),

	BUSY("busy"),

	ACTIVE("active");

	private final String value;

	Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
