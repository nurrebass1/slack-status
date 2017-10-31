package slack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import slack.beans.UserList;
import slack.constants.Config;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@Service public class SlackLookupService {

	@Autowired private UserList userList;

	private static final Logger logger = LoggerFactory.getLogger(SlackLookupService.class);

	private final RestTemplate restTemplate;

	public SlackLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async public CompletableFuture<User> findUser(String userName) {
		String url = String
				.format("https://slack.com/api/users.getPresence?token=%s&pretty=1&user=%s",
						Config.SLACK_TOKEN, userName);

		logger.debug(url);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));

		HttpEntity<User> entity = new HttpEntity<User>(headers);

		ResponseEntity<User> result = restTemplate
				.exchange(url, HttpMethod.GET, entity, User.class);

		User slackUser = result.getBody();
		String userPresence = slackUser.getPresence();
		userList.addOrUpdateUserStatus(slackUser, userName);
		logger.info(UsersMap.getUsersMap().get(userName) + " = " + userPresence);
		// Artificial delay of 1s for demonstration purposes
		/*try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }*/
		return CompletableFuture.completedFuture(slackUser);
	}

}
