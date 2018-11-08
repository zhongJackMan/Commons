package com.jackman.commons.redis.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;

/**
 * @Author shusheng
 * @Date 18/11/7 上午10:25
 */
public class FastJsonSerializer<T> implements RedisSerializer<T> {

    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Class<T> clazz;

    public FastJsonSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Nullable
    @Override
    public byte[] serialize(@Nullable T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Nullable
    @Override
    public T deserialize(@Nullable byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return (T) JSON.parseObject(str, clazz);
    }
}
