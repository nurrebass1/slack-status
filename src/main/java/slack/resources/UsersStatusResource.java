package slack.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import slack.UsersMap;
import slack.beans.ResponseUsersStatus;
import slack.beans.UserList;
import slack.beans.UserStatus;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/slack/users/status")
public class UsersStatusResource {

	@Autowired UserList userList;

	@RequestMapping(method = RequestMethod.GET)
	public List<ResponseUsersStatus> getUsersStatus() {

		List<ResponseUsersStatus> alRuu = userList.getUserStatusList().stream()
				.map(this::getResponseUserStatus).collect(Collectors.toList());

		return alRuu;

	}

	public ResponseUsersStatus getResponseUserStatus(UserStatus userStatus) {
		ResponseUsersStatus rus = new ResponseUsersStatus();
		rus.setBusyNow(userStatus.isBusyNow());
		rus.setRemainingQuota(
				userStatus.getMaxAllowedBusySeconds() - userStatus.getBusySinceTodayInSeconds());
		rus.setUserName(UsersMap.getUsersMap().get(userStatus.getUserName()));
		return rus;
	}

}
