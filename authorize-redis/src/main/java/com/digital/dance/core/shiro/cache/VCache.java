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


	/****
	 *
	 *
	 * 这里老有同学问，这个VCache和 {@link JedisManager} 有什么区别，
	 *
	 * 没啥区别，我只是想把shiro的操作和 业务Cache的操作分开
	 *
	 *
	 *
	 */


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

				e.printStackTrace();
			}
		}
		return J;
	}

	public static Set<String> getKeysByPrefix(String prefix){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
			Set<String> set = jedis.getKeysByPrefix(prefix +"*");
			return set;
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		}
//		finally {
//			returnResource(jedis, isBroken);
//		}
		return null;
	}

	public static int getKeysCountByPrefix(String prefix){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
			int set = jedis.getKeysCountByPrefix("*" + prefix +"*");
			return set;
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		}
//		finally {
//			finallyreturnResource(jedis, isBroken);
//		}
		return 0;
	}

	public static void delKeysByPrefix(String prefix){
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
			jedis.delKeysByPrefix("*" + prefix + "*");
//			Iterator<String> it = set.iterator();
//
//			keys = new String[set.size()];
//
//
//			while(it.hasNext()){
//				String keyStr = it.next();
//				keys[i] = keyStr;
//				i++;
//
//				//jedis.del(keyStr);
//				//delByKey(keyStr);
//			}
//			if(i > 0){
//				jedis.del(keys);
//				delByKey(keys);
//			}
			LoggerUtils.fmtDebug(VCache.class," [%s] redis key: del成功",  "*" + prefix + "*" );
		} catch (Exception e) {
			isBroken = true;
			LoggerUtils.fmtError(VCache.class, e, " [%s] redis key: del失败", "*" + prefix + "*" );
			e.printStackTrace();
		}
//		finally {
//			returnResource(jedis, isBroken);
//		}

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
//			jds.select(0);
//			byte[] skey = SerializeUtil.serialize(key);
//			return SerializeUtil.deserialize(jds.get(skey),requiredType);
			return jedis.get(key, requiredType);
        } catch (Exception e) {
            isBroken = true;
            e.printStackTrace();
        }
//        finally {
//            returnResource(jds, isBroken);
//        }
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
//			jds.select(0);
//			byte[] skey = SerializeUtil.serialize(key);
//			byte[] svalue = SerializeUtil.serialize(value);
			jedis.set(key, value);
        } catch (Exception e) {
            isBroken = true;
            e.printStackTrace();
        }
//        finally {
//            returnResource(jds, isBroken);
//        }
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
//			jds.select(0);
//			byte[] skey = SerializeUtil.serialize(key);
//			byte[] svalue = SerializeUtil.serialize(value);
			jedis.setex(key, timer, value);
        } catch (Exception e) {
            isBroken = true;
            e.printStackTrace();
        }
//        finally {
//            returnResource(jds, isBroken);
//        }
		
	}
	public static <K> Boolean expire(String key, int timeout, final TimeUnit unit) {
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
//			jds.select(0);
//			byte[] skey = SerializeUtil.serialize(key);
//			long rawTimeout = TimeoutUtils.toSeconds(timeout, unit);
//			//jds.expire(skey, (int)rawTimeout);
			jedis.expire(key, timeout, unit);
			isBroken = true;
		} catch (Exception e) {

			e.printStackTrace();
		}
//		finally {
//			returnResource(jds, isBroken);
//		}
		return isBroken;
	}

	public static <K> Boolean expire(String key, long milliseconds) {
		//JedisManager J = SpringContextUtil.getBean("jedisManager", JedisManager.class);
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
//			jds.select(0);
//			byte[] skey = SerializeUtil.serialize(key);

			//jds.expire(skey, (int)rawTimeout);
			jedis.expire(key, milliseconds);
			isBroken = true;
		} catch (Exception e) {

			e.printStackTrace();
		}
//		finally {
//			returnResource(jds, isBroken);
//		}
		return isBroken;
	}

	public static void expire(int dbIndex, String key, int expireTime)
			throws Exception {
		Codis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJ().getCodis();
//			jedis.select(dbIndex);
			//jedis.set(key, value);
			if (expireTime > 0)
				jedis.expire(dbIndex, key, expireTime);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		}
//		finally {
//			returnResource(jedis, isBroken);
//		}
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
//			jds.select(0);
//			byte[] mkey = SerializeUtil.serialize(mapkey);
//			byte[] skey = SerializeUtil.serialize(key);
//			List<byte[]> result = jds.hmget(mkey, skey);
//			if(null != result && result.size() > 0 ){
//				byte[] x = result.get(0);
//				T resultObj = SerializeUtil.deserialize(x, requiredType);
//				return resultObj;
//			}
			return jedis.getVByMap( mapkey, key, requiredType);
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		}
//		finally {
//			returnResource(jds, isBroken);
//		}
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
//			jds.select(0);
//			byte[] mkey = SerializeUtil.serialize(mapkey);
//			byte[] skey = SerializeUtil.serialize(key);
//			byte[] svalue = SerializeUtil.serialize(value);
//			jds.hset(mkey, skey,svalue);
			jedis.setVByMap( mapkey, key, value );
        } catch (Exception e) {
            isBroken = true;
            e.printStackTrace();
        }
//        finally {
//            returnResource(jds, isBroken);
//        }
		
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
			//jds.select(0);
//			byte[][] dx = new byte[dkey.length][];
//			for (int i = 0; i < dkey.length; i++) {
//				dx[i] = SerializeUtil.serialize(dkey[i]);
//			}
//			byte[] mkey = SerializeUtil.serialize(mapKey);
//			Long result = jds.hdel(mkey, dx);
			return jedis.delByMapKey( mapKey, dkey );
		} catch (Exception e) {
			isBroken = true;
			e.printStackTrace();
		}
//		finally {
//			returnResource(jds, isBroken);
//		}
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
//			jds.select(0);
//			byte[] lkey = SerializeUtil.serialize(setKey);
//			Set<T> set = new TreeSet<T>();
//			Set<byte[]> xx = jds.smembers(lkey);
//			for (byte[] bs : xx) {
//				T t = SerializeUtil.deserialize(bs, requiredType);
//				set.add(t);
//			}
			return jedis.getVByList( setKey, requiredType );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//			Long result = jds.scard(setKey);
			return jedis.getLenBySet( setKey );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//			Long result = 0L;
//			if(null == dkey){
//				result = jds.srem(key);
//			}else{
//				result = jds.del(key);
//			}
			return jedis.delSetByKey( key, dkey );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//			String result = jds.srandmember(key);
			return jedis.srandmember( key );
		} catch (Exception e){ 
			isBroken = true;
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
//			jds.select(0);
//			jds.sadd(setKey, value);
			jedis.setVBySet( setKey, value);
		} catch (Exception e) {
            isBroken = true;
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
//			jds.select(0);
//			Set<String> result = jds.smembers(key);
			return jedis.getSetByKey( key );
		} catch (Exception e) {
            isBroken = true;
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
//			jds.select(0);
//			byte[] lkey = SerializeUtil.serialize(listKey);
//			byte[] svalue = SerializeUtil.serialize(value);
//			jds.rpush(lkey, svalue);
			jedis.setVByList( listKey, value );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//
//			jds.rpush(listKey, value);
			jedis.setKVByList( listKey, value );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//			byte[] lkey = SerializeUtil.serialize(listKey);
//			//List<byte[]> valueBs = new ArrayList<>();
//			for(Object obj : values){
//				byte[] svalue = SerializeUtil.serialize(obj);
//				//valueBs.add(svalue);
//				jds.rpush(lkey, svalue);
//			}
			jedis.setVByListMutiElements( listKey, values );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//			byte[] lkey = SerializeUtil.serialize(listKey);
//			List<T> list = new ArrayList<T>();
//			List<byte[]> xx = jds.lrange(lkey,start,end);
//			for (byte[] bs : xx) {
//				T t = SerializeUtil.deserialize(bs, requiredType);
//				list.add(t);
//			}
			return jedis.getVByList( listKey, start, end, requiredType );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//			byte[] lkey = SerializeUtil.serialize(listKey);
//			Long result = jds.llen(lkey);
			return jedis.getLenByList( listKey );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//			byte[][] dx = new byte[dkey.length][];
//			for (int i = 0; i < dkey.length; i++) {
//				dx[i] = SerializeUtil.serialize(dkey[i]);
//			}
//			Long result = jds.del(dx);
			return jedis.delByKey( dkey );
		} catch (Exception e) {
			isBroken = true;
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
//			jds.select(0);
//			byte[] lkey = SerializeUtil.serialize(existskey);
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
//        if (isBroken)
//            J.getJedisPool().returnBrokenResource(jedis);
//        else
//        	J.getJedisPool().returnResource(jedis);
//        版本问题
        jedis.close();
	}
}
