package redisTest;

import java.util.ArrayList;
import java.util.List;

public class Redis {
	/**
	 * 向redis存订单map
	 * @param order
	 */
	public void saveOrderToRedis(Order order) {
		GetRedisCluster redisCluster = new GetRedisCluster();
		String value ="net_type"+" "+order.getNet_type()+","+"serial_no"+" "+order.getSerial_no()+","+"user_id"+" "+order.getUser_id()+","+"net_code"+" "+order.getNet_type()+","
				+"main_server_id "+order.getMain_server_id()+","+"accept_date "+order.getAccept_date()+","+"depart_name "+order.getDepart_name()+","+"staff_id "+order.getStaff_id()+","
				+"staff_name "+order.getStaff_name();
		redisCluster.getRedisCluster().set(order.getId(), value);
	}
	/**
	 * 从redis取订单
	 * @param key
	 * @return Order
	 */
	public Order getOrderFromRedis(String key) {
		GetRedisCluster redisCluster = new GetRedisCluster();
		System.out.println(key+","+redisCluster.getRedisCluster().get(key));
		Order order = new Order();
		
		String [] orderString =  redisCluster.getRedisCluster().get(key).split(",");
		List<String> orderString1 = new ArrayList<String>() ;
		String [] orderString2 = null;
		for( int i = 0;i<orderString.length;i++){
				//System.out.println(orderString[i]);
				orderString2 = orderString[i].split(" ");
				orderString1.add(orderString2[0]);
				orderString1.add(orderString2[1]);
				//System.out.println(orderString1.get(i));				
			}
		order.setId(key);
		order.setNet_type((String)orderString1.get(1));
		order.setSerial_no((String)orderString1.get(3));
		order.setUser_id((String)orderString1.get(5));
		order.setNet_code((String)orderString1.get(7));
		order.setMain_server_id((String)orderString1.get(9));
		order.setAccept_date((String)orderString1.get(11));
		order.setDepart_name((String)orderString1.get(13));
		order.setStaff_id((String)orderString1.get(15));
		order.setStaff_name((String)orderString1.get(17));
		return order;
	}

}
