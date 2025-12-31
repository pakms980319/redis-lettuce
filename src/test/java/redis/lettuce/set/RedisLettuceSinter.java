package redis.lettuce.set;

import org.junit.jupiter.api.Test;
import redis.lettuce.CommandAction;
import redis.lettuce.CommandTemplate;

import java.util.Set;

public class RedisLettuceSinter {

	String mainKey = "lettce:set:";
	String shinKey = "shin";
	String jungKey = "jung";
	String gangKey = "gang";

	@Test
	public void setAdvanced() {
		CommandAction action = (redisCommands -> {
			// sadd
			redisCommands.sadd(shinKey, "1", "2", "3", "5");
			redisCommands.sadd(jungKey, "3", "4", "5", "6");
			redisCommands.sadd(gangKey, "4", "5", "7");

			// smembers
			System.out.println(shinKey + " = " + redisCommands.smembers(shinKey));
			System.out.println(jungKey + " = " + redisCommands.smembers(jungKey));
			System.out.println(gangKey + " = " + redisCommands.smembers(gangKey));

			// sinter (교집합)
			Set<String> sinter = redisCommands.sinter(shinKey, jungKey, gangKey);
			System.out.println("sinter = " + sinter);
			
			// sunion (합집합)
			Set<String> sunion = redisCommands.sunion(jungKey, shinKey, gangKey);
			System.out.println("sunion = " + sunion);

			// sdiff (차집합)
			Set<String> sdiff = redisCommands.sdiff(shinKey, jungKey, gangKey);
			System.out.println("sdiff = " + sdiff);
		});
		CommandTemplate.commandAction(action);
	}
}
