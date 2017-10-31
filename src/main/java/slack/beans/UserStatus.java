package slack.beans;

import slack.constants.Config;

public class UserStatus {

	public UserStatus(String userName) {
		this.isBusyNow = false;
		this.userName = userName;
		this.busySinceTodayInSeconds = 0;
		this.maxAllowedBusySeconds = Config.MAX_ALLOWED_BUSY_SECONDS;
	}

	public int availableAfter() {
		return (this.maxAllowedBusySeconds > this.busySinceTodayInSeconds) ?
				(this.maxAllowedBusySeconds - this.busySinceTodayInSeconds) :
				0;
	}

	public void incrementBusyDuration(int secondsToIncrement) {
		if(isBusyNow()) {
			this.busySinceTodayInSeconds += secondsToIncrement;
		}
	}

	public Boolean isBusyNow() {
		if(!this.isBusyNow) {
			return false;
		}
		else {
			return this.maxAllowedBusySeconds > this.busySinceTodayInSeconds;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getBusySinceTodayInSeconds() {
		return busySinceTodayInSeconds;
	}

	public void setBusySinceTodayInSeconds(Integer busySinceTodayInSeconds) {
		this.busySinceTodayInSeconds = busySinceTodayInSeconds;
	}

	public Integer getMaxAllowedBusySeconds() {
		return maxAllowedBusySeconds;
	}

	public void setMaxAllowedBusySeconds(Integer maxAllowedBusySeconds) {
		this.maxAllowedBusySeconds = maxAllowedBusySeconds;
	}

	private String userName;
	private Integer busySinceTodayInSeconds;
	private Integer maxAllowedBusySeconds;
	private Boolean isBusyNow;

	public void setBusyNow(Boolean busyNow) {
		isBusyNow = busyNow;
	}
}
