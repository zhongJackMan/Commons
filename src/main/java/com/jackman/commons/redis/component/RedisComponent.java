package com.jackman.commons.redis.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * redis组件  key必须为String类型
 * @Author shusheng
 * @Date 18/11/7 上午11:37
 */
@Component
public class RedisComponent {
    /**
     * 默认过期时间
     */
    private final static Long DEFAULT_EXPIRE_TIME = 5 * 60 * 1000L;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 以String存储的值获取
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(final String key, final Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置String类型的存储
     * 默认存储时间为5分钟
     * @param key
     * @param target
     * @param <T>
     */
    public <T> void set(final String key, final T target) {
        redisTemplate.opsForValue().set(key, target, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 带有过期时间的设值
     * 过期时间单位为 毫秒
     * @param key
     * @param target
     * @param expireTime
     * @param <T>
     */
    public <T> void set(final String key, final T target, final long expireTime) {
        redisTemplate.opsForValue().set(key, target, expireTime);
    }

    /**
     * 设置单个Map中的key相对应的值
     * @param key
     * @param hashKey
     * @param target
     * @param <T>
     */
    public <T> void setHash(final String key, final String hashKey, final T target) {
        redisTemplate.opsForHash().put(key, hashKey, target);
    }

    /**
     * 将map整个放进去redis
     * @param key
     * @param target
     */
    public void setHash(final String key, final Map target) {
        redisTemplate.opsForHash().putAll(key, target);
    }

    /**
     * 获取hash中单个值
     * @param key
     * @param hashKey
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getHash(final String key, final String hashKey, final Class<T> clazz) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取redis分布式锁
     * 0. setIfAbsent 若成功, 则获取锁成功;
     * 1. 失败, 需判断上次获取锁是否过期, 未过期则获取锁失败;
     * 2. 锁已过期, 需判断是否有其他线程释放该锁;
     * @param key
     * @param expireTime
     * @return
     */
    public boolean tryAcquire(final String key, final long expireTime) {
        try {
            long currentTime = System.currentTimeMillis() + expireTime + 1L;

            for(int i = 0; i < 3; i++) {
                if(redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(currentTime))) {
                    return true;
                }

                String oldTimeStr = this.get(key, String.class);
                if(StringUtils.isNotBlank(oldTimeStr)) {
                    long oldTime = Long.parseLong(oldTimeStr);
                    if(oldTime < System.currentTimeMillis()) {
                        String tempTime = (String)
                                redisTemplate.opsForValue().getAndSet(key, String.valueOf(currentTime));
                        return oldTimeStr.equals(tempTime);
                    }
                }
            }

            return false;

        }catch(Exception e) {
            return false;
        }

    }

    /**
     * 0 存储的时间是否大于当前时间 大于则删除
     * @param key
     */
    public void releaseLock(final String key) {
        String lockTime = this.get(key, String.class);
        boolean needDelete = StringUtils.isNotBlank(lockTime) &&
                System.currentTimeMillis() > Long.parseLong(lockTime);
        if(needDelete) {
            redisTemplate.delete(key);
        }
    }
}
