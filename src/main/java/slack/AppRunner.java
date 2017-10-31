package slack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component public class AppRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final SlackLookupService slackLookupService;

	public AppRunner(SlackLookupService slackLookupService) {
		this.slackLookupService = slackLookupService;
	}

	public void run() throws Exception {
		// Start the clock
		long start = System.currentTimeMillis();
		Map<String, String> usersMap = UsersMap.getUsersMap();

		// Kick of multiple, asynchronous lookups
        List<String> userIds = usersMap.keySet().stream().collect(Collectors.toList());

		List<CompletableFuture<User>> cfu = userIds.stream().map(slackLookupService::findUser)
				.collect(Collectors.toList());

		// Wait until they are all done
		CompletableFuture.allOf(cfu.toArray(new CompletableFuture[userIds.size()])).join();

		// Print results, including elapsed time
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));


		/*cfu.forEach(x -> {
			try {
				logger.info("--> " + x.get());
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			catch (ExecutionException e) {
				e.printStackTrace();
			}
		});*/


	}

}
