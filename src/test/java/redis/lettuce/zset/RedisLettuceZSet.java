package redis.lettuce.zset;

import io.lettuce.core.Range;
import org.junit.jupiter.api.Test;
import redis.lettuce.CommandAction;
import redis.lettuce.CommandTemplate;

import java.util.List;

public class RedisLettuceZSet {

	String key = "lettuce:zset";
	String[] memberArray = "a,b,c,d,e,f,g".split(",");

	@Test
	public void zSet() {
		CommandAction action = (redisCommands -> {
			int score = 0;
			for (String member : memberArray) {
				// zadd
				redisCommands.zadd(key, score++, member);
			}

			// zrange
			List<String> zrange = redisCommands.zrange(key, 0, -1);
			System.out.println("zrange = " + zrange);
			
			// zrevrange
			List<String> zrevrange = redisCommands.zrevrange(key, 0, -1);
			System.out.println("zrevrange = " + zrevrange);

			// zcard -> scard
			Long zcard = redisCommands.zcard(key);
			System.out.println("zcard = " + zcard);

			// zcound
			Long zcount = redisCommands.zcount(key, Range.create(0, 5));
			System.out.println("zcount = " + zcount);

			// zrem
			redisCommands.zrem(key, "a", "b", "c");
			System.out.println("redis = " + redisCommands.zrange(key, 0, -1));
		});
		CommandTemplate.commandAction(action);
	}
}
