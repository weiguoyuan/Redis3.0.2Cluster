import redis.clients.jedis.JedisCluster;


public class TestRedis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetRedisCluster redisCluster = new GetRedisCluster();
		JedisCluster redis = redisCluster.getRedisCluster();
//		redisCluster.getRedisCluster().flushDB();
//		redisCluster.getRedisCluster().set("uushdask", "1123");
		for(int i=0;i<1000;i++){
			System.out.println(System.currentTimeMillis());
			redis.set(i+"","weiguoyuan");
			System.out.println(System.currentTimeMillis());
			System.out.println(i);
		}

	}

}
