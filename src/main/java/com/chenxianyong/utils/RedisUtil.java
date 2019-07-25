package com.chenxianyong.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	private static String ip = "47.94.251.74";
	private static Integer port = 6379;
	private static String pwd = "okejia88";
	private static int MAX_POOL = 1000;
	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;
	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
	private static int MAX_WAIT = 10000;
	// 连接超时的时间
	private static int TIMEOUT = 10000;

	/*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑以上配置均应在配置中心配置↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*/

	private static JedisPool jedisPool;
	
	/**
	 * @Description 获取操作redis的Jedis
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param ip            redis地址
	 * @param port          redis端口号
	 * @param pwd           redis连接密码
	 * @param connectManner redis连接方式，1为直连，否则为默认连接
	 * @return Java操作redis的Jedis
	 */
	private static Jedis getJedis(String ip, int port, String pwd, Integer connectManner) {

		if (connectManner != null && connectManner == 1) {
			Jedis jedis = new Jedis(ip, port);
			jedis.auth(pwd);
			jedis.connect();
			return jedis;
		}

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(MAX_POOL);
		config.setMaxTotal(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT);
		jedisPool = new JedisPool(config, ip, port, TIMEOUT, pwd);
		return jedisPool.getResource();
	}

	/**
	 * @Description 关闭jedis
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param jedis
	 */
	private static void closedJedis(Jedis jedis) {
		jedisPool.close();
	}

	/**
	 * @Description 根据key设置
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key   键
	 * @param value 值
	 * @return 是否成功
	 */
	public static boolean set(String key, String value) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		String result = jedis.set(key, value);
		closedJedis(jedis);
		if (!"OK".equals(result)) {
			return false;
		}
		return true;
	}

	/**
	 * @Description 根据key获取
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key 键
	 * @return String类型的值
	 */
	public static String get(String key) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		String result = jedis.get(key);
		closedJedis(jedis);
		return result;
	}

	/**
	 * @Description 判断key是否存在
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key 键
	 * @return 是否存在
	 */
	public static Boolean exists(String key) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Boolean result = jedis.exists(key);
		closedJedis(jedis);
		return result;
	}

	/**
	 * @Description 递增
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key 键
	 * @return 递增后的值
	 */
	public static Long incr(String key) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Boolean exists = jedis.exists(key);
		// 不存在
		if (!exists) {
			// 先设置为0，然后自增
			jedis.set(key, "0");
		}
		Long value = jedis.incr(key);
		closedJedis(jedis);
		return value;
	}

	/**
	 * @Description 按增量增加
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key   键
	 * @param value 增量
	 * @return 增后的值
	 */
	public static Long incrBy(String key, long incr) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Boolean exists = jedis.exists(key);
		// 不存在
		if (!exists) {
			// 先设置为0
			jedis.set(key, "0");
		}
		Long incrBy = jedis.incrBy(key, incr);
		closedJedis(jedis);
		return incrBy;
	}

	/**
	 * @Description 递减
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key
	 * @return 递减后的值，如不存在，或值<=0，则直接返回0并将key的值设置为0
	 */
	public static Long decr(String key) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Boolean exists = jedis.exists(key);
		// 不存在
		if (!exists) {
			jedis.set(key, "0");
			return 0L;
		}
		String value = get(key);
		if (Long.parseLong(value) <= 0) {
			jedis.set(key, "0");
			return 0L;
		}
		Long decr = jedis.decr(key);
		closedJedis(jedis);
		return decr;
	}

	/**
	 * @Description 按减量减
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key  键
	 * @param decr 减量
	 * @return 减后的值，如果key不存在或key对应的值<=0或key对应的值<=减量，则值设置为0
	 */
	public static Long decrBy(String key, long decr) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Boolean exists = jedis.exists(key);
		// 不存在
		if (!exists) {
			jedis.set(key, "0");
			return 0L;
		}
		String value = get(key);
		if (Long.parseLong(value) <= 0 || Long.parseLong(value) <= decr) {
			jedis.set(key, "0");
			return 0L;
		}
		Long decrBy = jedis.decrBy(key, decr);
		closedJedis(jedis);
		return decrBy;
	}

	/**
	 * @Description 为key设置有效时间
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key     键
	 * @param seconds 有效时间（以秒为单位，XX秒后过期 ==>> 有效期）
	 * @return 是否成功
	 */
	public static boolean setExpire(String key, int seconds) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Long expire = jedis.expire(key, seconds);
		closedJedis(jedis);
		if (expire == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @Description 为key设置过期时间（此方法暂未验证）
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key      键
	 * @param unixTime 时间戳(秒) （到这个时间过期 ==>> 有效期至）
	 * @return 是否成功
	 */
	public static boolean setExpireAt(String key, long unixTime) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Long expire = jedis.expireAt(key, unixTime);
		closedJedis(jedis);
		if (expire == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Description 删除key
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key 键
	 * @return 是否成功
	 */
	public static boolean del(String key) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Boolean exists = exists(key);
		if (!exists) {
			return true;
		}
		Long del = jedis.del(key);
		if (del > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Description 查询key的剩余有效时间
	 * @author ChenXianyong
	 * @date 2019年7月11日
	 * @param key 键
	 * @return 以秒为单位的剩余时间
	 */
	public static Long getTTL(String key) {
		Jedis jedis = getJedis(ip, port, pwd, 0);
		Boolean exists = exists(key);
		if (!exists) {
			return 0L;
		}
		Long ttl = jedis.ttl(key);
		return ttl;
	}
}
