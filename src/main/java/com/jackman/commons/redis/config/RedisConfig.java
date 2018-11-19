package com.jackman.commons.redis.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.jackman.commons.redis.serializer.FastJsonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 *
 * warn 需要application.properties文件中 设置fastJson白名单
 * @Author shusheng
 * @Date 18/11/7 上午10:20
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${fast-json.allowPath}")
    private String allowPath;

    @Primary
    @Bean("redisTemplate")
    public RedisTemplate<Object, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<Object, Serializable> redisTemplate = new RedisTemplate<>();

        FastJsonSerializer<Object> serializer = new FastJsonSerializer<>(Object.class);

        ParserConfig.getGlobalInstance().addAccept(allowPath);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
