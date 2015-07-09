import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class GetRedisCluster {
	/**
	 * 获得redis集群连接
	 * @return
	 */
	public JedisCluster getRedisCluster(){
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		//Jedis Cluster will attempt to discover cluster nodes automatically
		jedisClusterNodes.add(new HostAndPort("10.64.4.57", 6379));
		jedisClusterNodes.add(new HostAndPort("10.64.4.57", 6380));
		jedisClusterNodes.add(new HostAndPort("10.64.4.95", 6379));
		//jedisClusterNodes.add(new HostAndPort("10.64.4.95", 6380));
		//jedisClusterNodes.add(new HostAndPort("10.64.4.57", 6381));
		//jedisClusterNodes.add(new HostAndPort("10.64.4.95", 6381));
		JedisCluster jc = new JedisCluster(jedisClusterNodes,500,10000);
		return jc;		
	}

}
