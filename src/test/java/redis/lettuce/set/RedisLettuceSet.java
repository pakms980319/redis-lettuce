package redis.lettuce.set;

import org.junit.jupiter.api.Test;
import redis.lettuce.CommandAction;
import redis.lettuce.CommandTemplate;

import java.util.Set;

public class RedisLettuceSet {

	@Test
	public void setBasic() {
		CommandAction action = (redisCommands -> {
			String key = "lettuce:set";

			// sadd
			redisCommands.sadd(key, "1", "2", "3");
			redisCommands.sadd(key, "3", "4", "5");
			redisCommands.sadd(key, "5", "6", "7");

			Set<String> smembers = redisCommands.smembers(key);
			smembers.forEach(s -> {
				System.out.println("s = " + s);
			});

			// scard
			Long scard = redisCommands.scard(key);
			System.out.println("scard = " + scard);

			// srem
			Long srem = redisCommands.srem(key, "1", "2");
			smembers = redisCommands.smembers(key);
			System.out.println("smembers = " + smembers);

			// sismember
			Boolean sismember = redisCommands.sismember(key, "4");
			System.out.println("sismember = " + sismember);
			sismember = redisCommands.sismember(key, "3");
			System.out.println("sismember = " + sismember);

			// smove
			String newKey = "lettuce:set_new";
			redisCommands.smove(key, newKey, "3");

			System.out.println(key + " = " + redisCommands.smembers(key));
			System.out.println(newKey + " = " + redisCommands.smembers(newKey));
		});
		CommandTemplate.commandAction(action);
	}
}
