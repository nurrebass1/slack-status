package slack.beans;

import org.springframework.stereotype.Component;
import slack.User;
import slack.constants.Config;
import slack.constants.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component public class UserList {

	public UserList() {
		userStatusList = new ArrayList<>();
	}

	private List<UserStatus> userStatusList;

	public List<UserStatus> getUserStatusList() {
		return userStatusList;
	}

	public void setUserStatusList(List<UserStatus> userStatusList) {
		this.userStatusList = userStatusList;
	}

	public UserStatus getUserStatus(String userName) {
		Optional<UserStatus> userStatus = userStatusList.stream()
				.filter(x -> x.getUserName().equals(userName)).findFirst();
		return userStatus.get(); //TODO: use Supplier. never use .get.
	}

	public void addOrUpdateUserStatus(User user, String userName) {
		//TODO: use consumer instead
		if (userStatusList.stream().filter(x -> x.getUserName().equals(userName)).findFirst()
				.isPresent()) {
			//update user information
			UserStatus userStatus = getUserStatus(userName);

			//TODO: check correct status from response
			if(user.getPresence().equals(Status.AWAY.getValue()) || user.getPresence().equals(Status.BUSY.getValue())) {
				userStatus.setBusyNow(true);
				// increment the busy counter
				userStatus.incrementBusyDuration(Config.LOOKUP_INTERVAL/1000);
			} else {
				userStatus.setBusyNow(false);
			}

		} else {
			UserStatus userStatus = new UserStatus(userName);
			userStatusList.add(userStatus);
		}
	}


}
