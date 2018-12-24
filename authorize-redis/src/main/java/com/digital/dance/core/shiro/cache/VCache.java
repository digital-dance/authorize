package com.digital.dance.core.shiro.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import com.digital.dance.common.utils.LoggerUtils;
import com.digital.dance.common.utils.SpringUtils;
import com.digital.dance.framework.codis.Codis;
import org.springframework.data.redis.core.TimeoutUtils;

import redis.clients.jedis.Jedis;

import com.digital.dance.common.utils.*;


@SuppressWarnings("unchecked")
public class VCache {

	//final static JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
	//static JedisManager J = SpringUtils.getBean("jedisManager");
	static JedisManager J;
	private VCache() {}
	public static void setJ(JedisManager pJ){

		if(pJ == null) {
			J = SpringUtils.getBean("jedisManager");
		} else {
			J = pJ;
		}

	}

	public static JedisManager getJ(){

		if(J == null) {
			try {
				J = SpringUtils.getBean("jedisManager");
			} catch (Exception e) {
				LoggerUtils.fmtError( VCache.class, e, " [%s] getJ失败", e.getMessage() );
				e.printStackTrace();
			}
		}
		return J;
	}

	public static String buildKey(String key){
		Codis jedis = null;

		try {
			jedis = getJ().getCodis();

			return jedis.buildKey( key );
		} catch (Exception e) {
			LoggerUtils.fmtError( VCache.class, e, " [%s] buildKey失败", e.getMessage() );
			e.printStackTrace();
		}

		return null;
	}

	public static Set<String> getKeysByPrefix(String prefix){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
			Set<String> set = jedis.getKeysByPrefix( "*" + prefix + "*" );
			return set;
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError(VCache.class, e, " [%s] redis key: getKeysByPrefix失败", "*" + prefix + "*" );
			e.printStackTrace();
		}

		return null;
	}

	public static int getKeysCountByPrefix(String prefix){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
			int set = jedis.getKeysCountByPrefix( "*" + prefix + "*" );
			return set;
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError(VCache.class, e, " [%s] redis key: getKeysCountByPrefix失败", "*" + prefix + "*" );
			e.printStackTrace();
		}

		return 0;
	}

	public static void delKeysByPrefix(String prefix){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
			jedis.delKeysByPrefix( "*" + prefix + "*" );

			LoggerUtils.fmtDebug(VCache.class," [%s] redis key: delKeysByPrefix成功",  "*" + prefix + "*" );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError(VCache.class, e, " [%s] redis key: delKeysByPrefix失败", "*" + prefix + "*" );
			e.printStackTrace();
		}

	}
	
	/**
	 * 简单的Get
	 * @param <T>
	 * @param key
	 * @param requiredType
	 * @return
	 */
	public static <T> T get(String key , Class<T> requiredType){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.get(key, requiredType);
        } catch (Exception e) {
            isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] get失败", e.getMessage() );
            e.printStackTrace();
        }

		return null;
	}
	/**
	 * 简单的set
	 * @param key
	 * @param value
	 */
	public static void set(String key ,Object value){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.set(key, value);
        } catch (Exception e) {
            isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] set失败", e.getMessage() );
            e.printStackTrace();
        }

	}
	/**
	 * 过期时间的
	 * @param key
	 * @param value
	 * @param timer （秒）
	 */
	public static void setex(String key, Object value, int timer) {
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.setex(key, timer, value);
        } catch (Exception e) {
            isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] setex失败", e.getMessage() );
            e.printStackTrace();
        }
		
	}
	public static <K> Boolean expire(String key, int timeout, final TimeUnit unit) {
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.expire(key, timeout, unit);
			isBroken = true;
		} catch (Exception e) {
			LoggerUtils.fmtError( VCache.class, e, " [%s] expire失败", e.getMessage() );
			e.printStackTrace();
		}

		return isBroken;
	}

	public static <K> Boolean expire(String key, long milliseconds) {
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.expire(key, milliseconds);
			isBroken = true;
		} catch (Exception e) {
			LoggerUtils.fmtError( VCache.class, e, " [%s] expire失败", e.getMessage() );
			e.printStackTrace();
		}

		return isBroken;
	}

	public static void expire(int dbIndex, String key, int expireTime)
			throws Exception {
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			if (expireTime > 0)
				jedis.expire(dbIndex, key, expireTime);
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] expire失败", e.getMessage() );
			throw e;
		}

	}

	/**
	 * 
	 * @param <T>
	 * @param mapkey map
	 * @param key	 map里的key
	 * @param requiredType value的泛型类型
	 * @return
	 */
	public static <T> T getVByMap(String mapkey, String key, Class<T> requiredType){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.getVByMap( mapkey, key, requiredType);
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] getVByMap失败", e.getMessage() );
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 
	 * @param mapkey map
	 * @param key	 map里的key
	 * @param value   map里的value
	 */
	public static void setVByMap(String mapkey, String key, Object value){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.setVByMap( mapkey, key, value );
        } catch (Exception e) {
            isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] setVByMap失败", e.getMessage() );
            e.printStackTrace();
        }

	}
	/**
	 * 删除Map里的值
	 * @param mapKey
	 * @param dkey
	 * @return
	 */
	public static Object delByMapKey(String mapKey, String... dkey){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.delByMapKey( mapKey, dkey );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] delByMapKey失败", e.getMessage() );
			e.printStackTrace();
		}

		return new Long(0);
	}
	
	/**
	 * 往redis里取set整个集合
	 * 
	 * @param <T>
	 * @param setKey

	 * @param requiredType
	 * @return
	 */
	public static <T> Set<T> getVByList(String setKey,Class<T> requiredType){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.getVByList( setKey, requiredType );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] getVByList失败", e.getMessage() );
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取Set长度
	 * @param setKey
	 * @return
	 */
	public static Long getLenBySet(String setKey){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.getLenBySet( setKey );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] getLenBySet失败", e.getMessage() );
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 删除Set
	 * @param dkey
	 * @return
	 */
	public static Long delSetByKey(String key,String...dkey){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.delSetByKey( key, dkey );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] delSetByKey失败", e.getMessage() );
			e.printStackTrace();
		}
		return new Long(0);
	}
	/**
	 * 随机 Set 中的一个值
	 * @param key
	 * @return
	 */
	public static String srandmember(String key){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.srandmember( key );
		} catch (Exception e){ 
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] srandmember失败", e.getMessage() );
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 往redis里存Set
	 * @param setKey
	 * @param value
	 */
	public static void setVBySet(String setKey,String value){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.setVBySet( setKey, value);
		} catch (Exception e) {
            isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] setVBySet失败", e.getMessage() );
            e.printStackTrace();
        }
	}
	/**
	 * 取set 
	 * @param key
	 * @return
	 */
	public static Set<String> getSetByKey(String key){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.getSetByKey( key );
		} catch (Exception e) {
            isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] getSetByKey失败", e.getMessage() );
            e.printStackTrace();
        }
        return null;
		 
	}
	
	
	/**
	 * 往redis里的List里存元素
	 * @param listKey
	 * @param value
	 */
	public static void setVByList(String listKey,Object value){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.setVByList( listKey, value );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] setVByList失败", e.getMessage() );
			e.printStackTrace();
		}
	}

	public static <T> List<T> getVByListKey(String listKey, Class<T> requiredType){
		long len = VCache.getLenByList(listKey);
		List<T> lRB = VCache.getVByList(listKey, 0, (int)len, requiredType);
		return lRB;
	}

	public static void setKVByList(String listKey,String value){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.setKVByList( listKey, value );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] setKVByList失败", e.getMessage() );
			e.printStackTrace();
		}
	}

	/**
	 * 往redis里存List
	 * @param listKey
	 * @param values
	 */
	public static <T> void setVByListMutiElements(String listKey, List<T> values){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			jedis.setVByListMutiElements( listKey, values );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] setVByListMutiElements失败", e.getMessage() );
			e.printStackTrace();
		}
	}
	/**
	 * 往redis里取list
	 * 
	 * @param <T>
	 * @param listKey
	 * @param start
	 * @param end
	 * @param requiredType
	 * @return
	 */
	public static <T> List<T> getVByList(String listKey, int start, int end, Class<T> requiredType){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.getVByList( listKey, start, end, requiredType );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] getVByList失败", e.getMessage() );
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取list长度
	 * @param listKey
	 * @return
	 */
	public static Long getLenByList(String listKey){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.getLenByList( listKey );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] getLenByList失败", e.getMessage() );
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 删除
	 * @param dkey
	 * @return
	 */
	public static Long delByKey(String...dkey){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.delByKey( dkey );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError( VCache.class, e, " [%s] delByKey失败", e.getMessage() );
			e.printStackTrace();
		}
		return new Long(0);
	}
	/**
	 * 判断是否存在
	 * @param existskey
	 * @return
	 */
	public static boolean exists(String existskey){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();

			return jedis.exists(existskey);
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 释放
	 * @param jedis
	 * @param isBroken
	 */
	public static void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;

        jedis.close();
	}
}
