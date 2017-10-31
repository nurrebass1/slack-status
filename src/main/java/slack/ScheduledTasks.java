package slack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import slack.beans.UserList;
import slack.constants.Config;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component public class ScheduledTasks {

	@Autowired private AppRunner appRunner;

	@Autowired private UserList userList;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = Config.LOOKUP_INTERVAL) public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));


		try {
			appRunner.run();

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		userList.getUserStatusList().stream()
				.forEach(x -> log.info(UsersMap.getUsersMap().get(x.getUserName()) + " is busy now : " + x.isBusyNow() + " + . Remaining Quota of being busy " + x.availableAfter() + " seconds"));

	}
}
