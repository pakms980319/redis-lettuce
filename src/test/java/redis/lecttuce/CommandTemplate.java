package redis.lecttuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class CommandTemplate {

	public static void commandAction(CommandAction action) {
		RedisClient redisClient = RedisClient.create(getRedisURI());
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> redisCommands = connection.sync();

		action.doInExecute(redisCommands);

		connection.close();
		redisClient.shutdown();
	}

	private static RedisURI getRedisURI() {
		return RedisURI.builder()
		               .withHost("127.0.0.1")
		               .withPort(6379)
		               .withDatabase(0)  // 0~15
		               .build();
	}
}
