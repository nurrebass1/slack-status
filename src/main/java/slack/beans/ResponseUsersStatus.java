package slack.beans;

public class ResponseUsersStatus {
	private String userName;
	private String currentStatus;
	private Boolean isBusyNow;
	private Integer remainingQuota;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Boolean getBusyNow() {
		return isBusyNow;
	}

	public void setBusyNow(Boolean busyNow) {
		isBusyNow = busyNow;
	}

	public Integer getRemainingQuota() {
		return remainingQuota;
	}

	public void setRemainingQuota(Integer remainingQuota) {
		this.remainingQuota = remainingQuota;
	}
}
