package redisTest;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryShardedJedis;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;

public class MysqlToRedis {

	public static void main(String[] args) {
		
		
//		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//		//Jedis Cluster will attempt to discover cluster nodes automatically
//		jedisClusterNodes.add(new HostAndPort("192.168.56.101", 6379));
//		jedisClusterNodes.add(new HostAndPort("192.168.56.101", 6380));
//		jedisClusterNodes.add(new HostAndPort("192.168.56.101", 6381));
//		JedisCluster jc = new JedisCluster(jedisClusterNodes,5000,1000);

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Order order = new Order();
		Order order1 = new Order();
		Redis redis = new Redis();
		//SaveToRedis str = new SaveToRedis();
		//GetRedis gr = new GetRedis();
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iom", "root", "weiguoyuan");//java.sql 包中 Connection 接口的实现
			stmt = con.createStatement();//通过Connection创建一个语句对象,语句对象可以执行语句
			rs = stmt.executeQuery("select * from e_crm_order");//定义一个结果集接受查询结果
			while(rs.next()){
				
				order.setId(rs.getString("srv_order_id"));
				order.setNet_type(rs.getString("net_type"));
				order.setSerial_no(rs.getString("serial_no"));
				order.setUser_id(rs.getString("user_id"));
				order.setNet_code(rs.getString("net_code"));
				order.setMain_server_id(rs.getString("main_server_id"));
				order.setAccept_date(rs.getString("accept_date"));
				order.setDepart_name(rs.getString("depart_name"));
				order.setStaff_id(rs.getString("staff_id"));
				order.setStaff_name(rs.getString("staff_name"));
				
				redis.saveOrderToRedis(order);
				order1 = redis.getOrderFromRedis(order.getId());
				System.out.println(order1.getId()+" "+ order.getNet_type()+" "+order1.getSerial_no()+" "+order1.getUser_id()+" "+order1.getNet_code()+" "+
									order1.getMain_server_id()+" "+order1.getAccept_date()+" "+order1.getDepart_name()+" "+order1.getStaff_id()+" "+order1.getStaff_name());
				
				Map<String,String> hm = new HashMap<String,String>();
				hm.put("net_type", rs.getString("net_type"));
				hm.put("serial_no",rs.getString("serial_no"));
				hm.put("user_id", rs.getString("user_id"));
				//jedis.hmset(key,hm);
				}
			}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		try{
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}

}
