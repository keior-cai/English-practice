package com.english.listening.common;

import com.english.listening.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis
 * @Author: Gentle
 * @date 2018/10/25 12:47
 */
@Component
@Slf4j
public class RedisService<T> {

    private static final Integer DEFAULT_TIME = -1;
    private static final Long DEFAULT_DATA = 1L;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 不设置过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, T value) {
        return set(key, value, DEFAULT_TIME);
    }

    /**
     * 设置过期时间，可以传空值。避免并发环境下缓存穿透
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, T value, int second) {
        try {
            String json = JsonUtil.ObjectTojson(value);
            if (second <= 0) {
                redisTemplate.opsForValue().set(key, json);
            } else {
                redisTemplate.opsForValue().set(key, json, second);
            }
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }


        return false;
    }

    /**
     * 获取相关键的值
     *
     * @param key
     * @return
     */

    public String get(String key) {
        try {

            return redisTemplate.opsForValue().get(key);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Boolean del(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 哈希存储，往哈希中设置相关属性，默认永久存在
     *
     * @param field
     * @param key
     * @param value
     */
    public void hashSet(String key, String field, T value) {
        hashSet(key, field, value, DEFAULT_TIME);
    }

    /**
     * 哈希存储，往哈希中设置相关属性，自定义时间
     *
     * @param field
     * @param key
     * @param value
     * @param time
     */
    public void hashSet(String key, String field, T value, long time) {

        String json = JsonUtil.ObjectTojson(value);
        try {
            if (time < 0) {
                redisTemplate.opsForHash().put(key, field, json);
            } else {
                redisTemplate.opsForHash().put(key, field, json);
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }

    public String hashGet(String key, String field) {

        try {

            return (String) redisTemplate.opsForHash().get(key, field);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 删除哈希中的属性
     *
     * @param field
     * @param key
     */
    public void hashDel(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 判断key存不存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {

        try {

            return redisTemplate.hasKey(key);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 自增，默认自增1
     *
     * @param key
     * @return
     */
    public long incr(String key) {

        return incr(key, DEFAULT_DATA);
    }

    /**
     * 自增，自定义加多少
     *
     * @param key
     * @param delta
     * @return
     */
    public long incr(String key, long delta) {
        try {

            if (delta < 0) {
                throw new RuntimeException("递增因子必须大于0");
            }
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 自减，自定义加多少
     *
     * @param key
     */
    public long decr(String key) {

        return decr(key, DEFAULT_DATA);
    }

    /**
     * 自减，自定义减少数量
     *
     * @param key
     * @param delta
     * @return
     */
    public long decr(String key, long delta) {
        try {

            if (delta < 0) {
                throw new RuntimeException("递减因子必须大于0");
            }
            return redisTemplate.opsForValue().increment(key, -delta);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public List<String> getMany(List<String> list) {

        try {
            if (list == null || list.isEmpty()) {
                throw new RuntimeException("前端传规格id为空");
            }

            return redisTemplate.opsForValue().multiGet(list);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }


}
