package com.aorun.attorney.util.cache.redis;


import java.util.Set;

public class RedisCache {


    public static void put(String key, String value) {
        try {
            new RedisUtil().set(key, value, 30 * 24 * 60 * 60);//最长1个月的秒数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void put(String key, String value, int minute) {
        try {
            new RedisUtil().set(key, value, minute * 60);//秒数
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void remove(String key) {
        new RedisUtil().del(key);
    }

    /******* redis hash ********/

    public static void hset(String key, String field, String value) {
        try {
            new RedisUtil().hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hget(String key, String field) {
        return new RedisUtil().hget(key, field);
    }

    public static Long hdel(String key, String field) {
        return new RedisUtil().hdel(key, field);
    }

    public static boolean hexists(String key, String field) {
        return new RedisUtil().hexists(key, field);
    }

    public static Long hincrby(String key, String field) {
        return new RedisUtil().hincrby(key, field, 1l);
    }

    public static Long hlen(String key) {
        return new RedisUtil().hlen(key);
    }

    public static Set<String> hkeys(String key) {
        return new RedisUtil().hkeys(key);
    }

//	public static Set<String> keys(String key){
//		return new JedisUtil().keys(key);
//	}

    public static void main(String[] args) {

//		System.out.println(new RedisUtil().hdel(RedisCachConstant.NEWS_HASH_CACHE+"2616", RedisCachConstant.NEWS_HASH_KEY_APPHINTS));
        RedisCache.put("18321218140test", "000000000000000000");
        //System.out.println(RedisCache.get("18321218140test"));
//		long del = new  RedisUtil().del("18321218140test");
//		System.out.println(del);

    }


}
